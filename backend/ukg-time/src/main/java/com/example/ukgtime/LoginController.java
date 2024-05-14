package com.example.ukgtime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/api/auth/login")
    public ResponseEntity<String> handlePostRequest(@RequestBody User user) {
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());

        // If authentication is successful, generate UID (or get it from database)
        String uid = "123456"; // Example UID

        // Create a response JSON containing UID
        String responseBody = "{\"uid\": \"" + uid + "\"}";

        // Return response with status 200 and UID in the body
        return ResponseEntity.ok(responseBody);
    }
}