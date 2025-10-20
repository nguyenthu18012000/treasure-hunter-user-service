package com.TreasureHunter.UserService.controller;

import com.TreasureHunter.CommonLib.dto.request.auth.LoginRequestDTO;
import com.TreasureHunter.CommonLib.dto.request.auth.RegisterRequestDTO;
import com.TreasureHunter.CommonLib.dto.response.user.UserResponseDTO;
import com.TreasureHunter.UserService.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@Valid() @RequestBody() RegisterRequestDTO request) {
        userService.register(request);
    }

    @PostMapping("/login")
    public UserResponseDTO login(@Valid() @RequestBody() LoginRequestDTO request) {
        return userService.login(request.getUsername(), request.getPassword());
    }
}
