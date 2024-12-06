package com.jssolutions.coffee_shop_api.infrastructure.log;

import com.jssolutions.coffee_shop_api.domain.Log;
import com.jssolutions.coffee_shop_api.infrastructure.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void logApiCall(String query, String response) {
        Log log = new Log();
        log.setQuery(query);
        log.setResponse(response);
        log.setTimestamp(LocalDateTime.now());

        logRepository.save(log);
    }
}
