package com.zubair.springBootPractice.healthCheck;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class HealthCheck {
    @GetMapping("health-check")
    public String healthCheck()
    {
        return "The application is up and running...";
    }
}
