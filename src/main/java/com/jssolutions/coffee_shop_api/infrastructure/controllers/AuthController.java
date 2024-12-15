package com.jssolutions.coffee_shop_api.infrastructure.controllers;

import com.jssolutions.coffee_shop_api.application.dto.LoginRequest;
import com.jssolutions.coffee_shop_api.application.usecase.AuthenticateUserUseCase;
import com.jssolutions.coffee_shop_api.application.usecase.RegisterUserUseCase;
import com.jssolutions.coffee_shop_api.domain.entities.User;
import com.jssolutions.coffee_shop_api.infrastructure.services.LogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final LogService logService;

    public AuthController(RegisterUserUseCase registerUserUseCase, AuthenticateUserUseCase authenticateUserUseCase, LogService logService) {
        this.registerUserUseCase = registerUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
        this.logService = logService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            logService.logApiCall("Request: POST /auth/signup - Payload: " + user, null);

            registerUserUseCase.execute(user);

            logService.logApiCall(null, "Response: Usuário registrado com sucesso.");

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
        } catch (IllegalArgumentException e) {
            logService.logApiCall(null, "Response: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            logService.logApiCall("Request: POST /auth/login - Payload: " + loginRequest, null);

            String token = authenticateUserUseCase.execute(loginRequest);

            logService.logApiCall(null, "Response: " + token);

            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            logService.logApiCall(null, "Response: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}


