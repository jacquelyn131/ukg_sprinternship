package com.example.ukgtime.Employee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/api/user/info")
    public ResponseEntity<Employee> getUserInfo() {
        // Simulate finding the user with ID 
        Employee newEmp = new Employee(123456, "1010101010", "Rafael", "Ojeda", "12/34/56", "rafael.e.ojeda@gmail.com", 654321);

        System.out.println("Test");
        // Return response with status 200 and user info in the body
        return ResponseEntity.ok(newEmp);
    }


//    @PostMapping("/api/user/location")
//    public ResponseEntity<Boolean> updateUserLocation(@RequestBody Boolean locationAvailable) {
//
//        System.out.println(locationAvailable);
//
//        return ResponseEntity.ok(true);
//    }
}
