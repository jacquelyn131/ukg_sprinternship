package com.example.ukgtime;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/api/greeting")
    @CrossOrigin( origins = "http://localhost:5173" )
    public Greeting greeting(@RequestParam(value="name", defaultValue = "World") String name) {
        System.out.println("Welcome back " + name);
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
