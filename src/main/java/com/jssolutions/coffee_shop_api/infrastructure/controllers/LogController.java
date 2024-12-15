package com.jssolutions.coffee_shop_api.infrastructure.controllers;

import com.jssolutions.coffee_shop_api.domain.entities.Log;
import com.jssolutions.coffee_shop_api.infrastructure.repositories.LogRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class LogController {
    private final LogRepository logRepository;

    public LogController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @GetMapping
    public ResponseEntity<List<Log>> getAllLogs() {
        List<Log> logs = logRepository.findAll();
        return ResponseEntity.ok(logs);
    }
}
