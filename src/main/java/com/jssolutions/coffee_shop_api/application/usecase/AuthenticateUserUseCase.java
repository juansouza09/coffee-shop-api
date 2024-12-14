package com.jssolutions.coffee_shop_api.application.usecase;

import com.jssolutions.coffee_shop_api.application.dto.LoginRequest;
import com.jssolutions.coffee_shop_api.domain.entities.CustomUserDetails;
import com.jssolutions.coffee_shop_api.domain.entities.JwtUtil;
import com.jssolutions.coffee_shop_api.domain.entities.User;
import com.jssolutions.coffee_shop_api.infrastructure.services.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateUserUseCase {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthenticateUserUseCase(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public String execute(LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas."));

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), loginRequest.getPassword()));

        return jwtUtil.generateToken(new CustomUserDetails(user));
    }
}

