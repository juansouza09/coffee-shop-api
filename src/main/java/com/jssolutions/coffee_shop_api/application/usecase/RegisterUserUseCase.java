package com.jssolutions.coffee_shop_api.application.usecase;

import com.jssolutions.coffee_shop_api.domain.entities.User;
import com.jssolutions.coffee_shop_api.infrastructure.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserUseCase {
    private final UserService userService;

    public RegisterUserUseCase(UserService userService) {
        this.userService = userService;
    }

    public void execute(User user) {
        userService.registerUser(user);
    }
}
