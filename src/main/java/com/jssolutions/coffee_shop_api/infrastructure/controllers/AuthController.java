package com.jssolutions.coffee_shop_api.infrastructure.controllers;

import com.jssolutions.coffee_shop_api.application.dto.LoginRequest;
import com.jssolutions.coffee_shop_api.application.usecase.AuthenticateUserUseCase;
import com.jssolutions.coffee_shop_api.application.usecase.RegisterUserUseCase;
import com.jssolutions.coffee_shop_api.domain.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegisterUserUseCase registerUserUseCase;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase, AuthenticateUserUseCase authenticateUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            registerUserUseCase.execute(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authenticateUserUseCase.execute(loginRequest);
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}


