package com.example.ukgtime;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @PostMapping("/api/login")
    public void handlePostRequest(@RequestBody String data) {
        System.out.println("Data received from frontend: " + data);
    }
}
