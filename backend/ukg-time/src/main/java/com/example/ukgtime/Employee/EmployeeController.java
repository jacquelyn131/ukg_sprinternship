package com.example.ukgtime.Employee;

import com.example.ukgtime.*;
import com.example.ukgtime.Company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.time.Clock;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private static CorporateEventDao<Employee> dao;
    private static CorporateEventDao<Company> companyDao;
    private static ClockPunchDao clockPunchDao;

    public EmployeeController(CorporateEventDao<Employee> dao, CorporateEventDao<Company> companyDao, ClockPunchDao clockPunchDao) {
        this.dao = dao;
        this.companyDao = companyDao;

        this.clockPunchDao = clockPunchDao;
    }

    public static boolean isWithinRange(double userLocation, double businessLocation, double range) {
        // Calculate the absolute difference
        double difference = Math.abs(userLocation - businessLocation);
        // Check if the difference is within the specified range
        return difference <= range;
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

            // Return response with status 200 (OK) and the created employee object in the body
            return ResponseEntity.status(HttpStatus.OK).body(getEmp);
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
    public ResponseEntity<Boolean> updateUserLocation(@RequestBody Coordinates userLoc) {
        boolean latCheck = isWithinRange(userLoc.getLatitude(), /* business lat */, 0.0002899);
        boolean longCheck = isWithinRange(userLoc.getLongitude(), /* business lat */, 0.0002899);
        boolean nearLocation = latCheck&&longCheck;
        System.out.println("Is the user witin the range? " + nearLocation);


        return ResponseEntity.ok(nearLocation);
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
}
