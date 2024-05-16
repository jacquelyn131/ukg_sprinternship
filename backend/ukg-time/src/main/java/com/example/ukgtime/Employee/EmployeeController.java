package com.example.ukgtime.Employee;

import com.example.ukgtime.ClockPunch;
import com.example.ukgtime.ClockPunchDao;
import com.example.ukgtime.Company.Company;
import com.example.ukgtime.Coordinates;
import com.example.ukgtime.CorporateEventDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        timeStamp.setOfficeId(3);
//        timeStamp.setType("IN");
        System.out.println(timeStamp);
//        System.out.println(clockPunchDao.add(timeStamp));
        return ResponseEntity.ok(true);
    }

    @PostMapping("/api/user/location")
    public ResponseEntity<Boolean> updateUserLocation(@RequestBody Coordinates userLoc) {

        System.out.println(userLoc);


        return ResponseEntity.ok(true);
    }

    @GetMapping("/api/user/viewRecentPunch")
    public ResponseEntity<Optional<ClockPunch>> viewRecentPunch(@RequestParam long id ) {
        System.out.println(id);
        ClockPunch recentPunch = (ClockPunch) clockPunchDao.getRecentPunch(id).get();
        System.out.println(recentPunch);
        // return response with status 200 ok and the most recent ClockPunch
        return ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(recentPunch));
    }

    @GetMapping("/api/user/viewRecentPunchList")
    public ResponseEntity<Optional<List<ClockPunch>>> viewRecentPunchList(@RequestParam long id ) {
        System.out.println(id);
        List<ClockPunch> punchList = clockPunchDao.employeePunchList(id);
        System.out.println(punchList);
        // return response with status 200 ok and the most recent ClockPunch
        return ResponseEntity.status(HttpStatus.OK).body(Optional.ofNullable(punchList));
    }
}
