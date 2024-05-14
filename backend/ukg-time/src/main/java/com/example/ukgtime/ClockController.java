package com.example.ukgtime;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClockController {
    @PostMapping("/api/user/newPunch")
    public ResponseEntity<String> handle() {
        return new ResponseEntity<String>(HttpStatusCode.valueOf(200));
    }
}
