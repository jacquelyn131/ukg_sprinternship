package com.example.ukgtime.Employee;

import com.example.ukgtime.Company.Company;
import com.example.ukgtime.Coordinates;
import com.example.ukgtime.CorporateEventDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

//    @PostMapping("/api/user/info")
//    public ResponseEntity<Employee> getUserInfo() {
//        // Simulate finding the user with ID
//        Employee newEmp = new Employee(123456, "1010101010", "Rafael", "Ojeda", "12/34/56", "rafael.e.ojeda@gmail.com", 654321);
//
//        System.out.println("Test");
//        // Return response with status 200 and user info in the body
//        return ResponseEntity.ok(newEmp);
//    }

    private static CorporateEventDao<Employee> dao;
    private static CorporateEventDao<Company> companyDao;
    public EmployeeController(CorporateEventDao<Employee> dao, CorporateEventDao<Company> companyDao) {
        this.dao = dao;
        this.companyDao = companyDao;
    }

        @PostMapping("/api/user/info")
        public ResponseEntity<Employee> createUserInfo(@RequestBody Employee employee) {
            // Here, you have access to the request body sent from the frontend, which is an Employee object
            // You can perform any necessary operations with the received Employee object
            // For example, you might save it to a database
            System.out.println(employee);
            System.out.println(dao.get(employee.getEmployeeId()));
            // Return response with status 200 (OK) and the created employee object in the body
            return ResponseEntity.status(HttpStatus.OK).body(employee);
        }


//    @PostMapping("/api/user/info")
//    public ResponseEntity<Employee> createUserInfo(@RequestBody Employee employee) {
//        // Here, you have access to the entire Employee object sent in the request body
//        System.out.println("Received employee: " + employee);
//
//        // You can perform any necessary operations with the received Employee object
//        // For example, you might save it to a database
//
//        // Return response with status 201 (Created) and the received employee object in the body
//        return ResponseEntity.status(HttpStatus.OK).body(employee);
//    }

    @PostMapping("/api/user/location")
    public ResponseEntity<Boolean> updateUserLocation(@RequestBody Coordinates userLoc) {

        System.out.println(userLoc);

        return ResponseEntity.ok(true);
    }
}
