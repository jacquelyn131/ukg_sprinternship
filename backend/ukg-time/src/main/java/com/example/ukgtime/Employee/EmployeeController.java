package com.example.ukgtime.Employee;

import com.example.ukgtime.*;
import com.example.ukgtime.Company.Company;
import com.example.ukgtime.Company.CompanyAddress;
import com.example.ukgtime.Company.CompanyLocation;
import com.google.api.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    private static JdbcCorporateEventDao dao;
    private static CorporateEventDao<Company> companyDao;
    private static ClockPunchDao clockPunchDao;
    private static EmployeeCompanyDao employeeCompanyDao;
    private static CompanyLocationDao companyLocationDao;
    private static CompanyAddressDao companyAddressDao;


    public EmployeeController(JdbcCorporateEventDao dao,
                              CorporateEventDao<Company> companyDao,
                              ClockPunchDao clockPunchDao,
                              EmployeeCompanyDao employeeCompanyDao,
                              CompanyLocationDao companyLocationDao,
                              CompanyAddressDao companyAddressDao
    ) {
        this.dao = dao;
        this.companyDao = companyDao;
        this.clockPunchDao = clockPunchDao;
        this.employeeCompanyDao = employeeCompanyDao;
        this.companyLocationDao = companyLocationDao;
        this.companyAddressDao = companyAddressDao;
    }

    public static boolean isWithinRange(double[] empCoords, double[] compCoords, double range) {
        double longitudeDifference = empCoords[0] - compCoords[1];
        double latitudeDifference = empCoords[1] - compCoords[0];

        System.out.println("Employee Coords" + empCoords[0] + " " + empCoords[1]);
        System.out.println("Comp Coords" + compCoords[1] + " " + compCoords[0]);

        double distance = Math.sqrt(Math.pow(longitudeDifference, 2) + Math.pow(latitudeDifference, 2));

        return distance <= range;
    }

    // helper method to calculate length of a shift
    public static float calculateShiftDuration(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat();
//        Date startDate = sdf.parse(startTime, new ParsePosition(0));
//        Date endDate = sdf.parse(endTime, new ParsePosition(0));
        String parseDate1 = startTime.replaceAll(" ", "T");
        String parseDate2 = endTime.replaceAll(" ", "T");
        System.out.println("parseDate1: " + parseDate1);
        parseDate1 = parseDate1 + ".00Z";
        parseDate2 = parseDate2 + ".00Z";
        Instant i1 = Instant.parse(parseDate1);
        Instant i2 = Instant.parse(parseDate2);
        System.out.println("i1: " + i1 + " i2: " + i2);
        long millis1 = i1.toEpochMilli();
        long millis2 = i2.toEpochMilli();
        System.out.println("millis1: " + millis1 + " millis2: " + millis2);
        Timestamp ts1;
        Timestamp ts2;
        float durationMilis = (float)(millis2 - millis1);
        System.out.println("durationmillis " + durationMilis);
        float hours = (durationMilis) / (float) 3600000;
        System.out.println("hours " + hours);
        return hours;
    }

    @PostMapping("/api/auth/login")
    public ResponseEntity<Boolean> loginUser(@RequestBody Employee employee) {
        System.out.println(dao.list());

        String empEmail = employee.getEmail();
        long empId = employee.getEmployeeId();

        if (!dao.find(empId)) {
            System.out.println("Failed to log in!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        } else {
            Optional<Employee> empInfo = dao.get(empId);

            if (!empInfo.get().getEmail().equals(empEmail)) {
                System.out.println("Failed to log in!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
            } else {
                System.out.println("Successfully logged in!");
                return ResponseEntity.status(HttpStatus.OK).body(true);

            }
        }

    }

    @PostMapping("/api/user/info")
    public ResponseEntity<Optional<Employee>> createUserInfo(@RequestBody Employee employee) {
        System.out.println(employee);
        System.out.println(dao.get(employee.getEmployeeId()));

        Optional<Employee> getEmp = dao.get(employee.getEmployeeId());

        // Return response with status 200 (OK) and the created employee object in the bod
        return ResponseEntity.status(HttpStatus.OK).body(getEmp);
    }


    @GetMapping("/api/listusers")
    public ResponseEntity<List<Employee>> loginUser() {
        return ResponseEntity.ok(dao.list());
    }

    @PostMapping("/api/add/timestamp")
    public ResponseEntity<Boolean> addTimeStamp(@RequestBody ClockPunch timeStamp) {
        System.out.println(timeStamp);
        timeStamp.setOfficeId(3);
        System.out.println(clockPunchDao.add(timeStamp));
        return ResponseEntity.ok(true);
    }

    /* NEEDS TO GET BUSINESS LOCATION */
    @PostMapping("/api/user/checkLocation")
    public ResponseEntity<Boolean>checkUserLocation(@RequestBody EmployeeCheckLocation empCheckLocation) {
        // Gets the company ID based on the employees company ID
        Optional<EmployeeCompany> empComp = employeeCompanyDao.get(empCheckLocation.getEmployeeId());
        long compId = empComp.get().getCompanyId();

        // List to iterate through every company
        List<CompanyAddress> compList = companyAddressDao.list();

        long comp_office_id = 1;
        for (CompanyAddress address : compList) {
            // If the current companyID matches the employees company ID match is found.
            if (address.getCompanyId() == compId)
                comp_office_id = address.getCompanyOfficeId();
        }
        System.out.println("comp_office_id: " + comp_office_id);

        Optional<CompanyLocation> compLoc = companyLocationDao.get(comp_office_id);
        System.out.println(compLoc.get().toString());
        double[] compCoords = compLoc.get().getLocation();
        double[] empCoords = new double[]{empCheckLocation.getLongitude(), empCheckLocation.getLatitude()};

        boolean withinRange = isWithinRange(empCoords, compCoords, 0.015);

        if (withinRange) {
            System.out.println("The points are within the specified range.");
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } else {
            System.out.println("The points are not within the specified range.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }

    }

    @GetMapping("/api/user/viewRecentPunch")
    public ResponseEntity<Optional<ClockPunch>> viewRecentPunch(@RequestParam long id ) {
        System.out.println(id);
        ClockPunch recentPunch = (ClockPunch) clockPunchDao.getRecentPunch(id).get();
        System.out.println(recentPunch);
        float duration = calculateShiftDuration("2024-05-17 08:00:00", "2024-05-17 14:55:55");
        // return response with status 200 ok and the most recent ClockPunch
        return ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(recentPunch));
    }

    @GetMapping("/api/user/viewRecentPunchList")
    public ResponseEntity<Optional<List<ClockPunch>>> viewRecentPunchList(@RequestParam long id ) {
        List<ClockPunch> punchList = clockPunchDao.employeePunchList(id);

        System.out.println("Retrieving EmployeeID: " +  id + " Recent Punch List Info.");
        System.out.println(punchList);
        // return response with status 200 ok and the most recent ClockPunch
        return ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(punchList));
    }
    @PostMapping("/api/user/viewRecentShift")
    public ResponseEntity<Optional<ShiftData>> viewRecentShift(@RequestBody Employee employee ) {
        long id = employee.getEmployeeId();
        System.out.println(id);
        String recentInPunch = (String) clockPunchDao.getRecentPunchTime(id, "IN").get();
        String recentOutPunch = (String) clockPunchDao.getRecentPunchTime(id, "OUT").get();
        System.out.println(recentInPunch);
        float duration = (float)calculateShiftDuration(recentInPunch, recentOutPunch);
        duration = (float)Math.floor((duration * 100.0f)/ 1.0f) / 100.0f;
        ShiftData shiftData = new ShiftData();
        shiftData.setShiftDuration(duration);
        shiftData.setEmployeeId(id);
        shiftData.setStartTime(recentInPunch.substring(11, 16));
        shiftData.setEndTime(recentOutPunch.substring(11, 16));
        System.out.println(recentInPunch.substring(0, 10));
        shiftData.setDate(recentInPunch.substring(0, 10));
        System.out.println(recentInPunch);
        System.out.println(shiftData.toString());
        // return response with status 200 ok and the most recent shift data
        return ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(shiftData));
    }

//###############################################################################
    // NEED TO BE TESTED
//###############################################################################

    @PostMapping("/api/user/add")
    public ResponseEntity<Boolean> addEmployee(@RequestBody Employee employee) {
        boolean result = dao.add(employee);

        if (!result) {
            System.out.println("Could not add: " + employee);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        System.out.println("Successfully added user: " + employee);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/api/user/delete")
    public ResponseEntity<Boolean> deleteEmployee(@RequestBody Employee employee) {
        boolean result = dao.delete(employee.getEmployeeId());

        if (!result) {
            System.out.println("Could not delete: " + employee);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        System.out.println("Successfully deleted user: " + employee);
        return ResponseEntity.ok(true);
    }

    //###############################################################################
    // NEED TO BE TESTED
//###############################################################################

  // check if an employee is a manager given an employee object, returns true if the employee
    //  is a manager for at least one employee in the database and false otherwise
    @GetMapping("/api/user/checkmanager")
    public ResponseEntity<Boolean> checkIsManager(@RequestParam long id) {
        Boolean isManager = null;
        if (dao.checkIsManager(id) ) {
            isManager = true;
        } else {
            isManager = false;
        }
        return ResponseEntity.status(HttpStatus.OK).body(isManager);
    }
}
