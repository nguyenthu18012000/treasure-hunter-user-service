package com.TreasureHunter.UserService.controller;

import com.TreasureHunter.CommonLib.constant.CommonConstant;
import com.TreasureHunter.CommonLib.dto.request.auth.LoginRequestDTO;
import com.TreasureHunter.CommonLib.dto.request.auth.RegisterRequestDTO;
import com.TreasureHunter.CommonLib.dto.response.user.UserResponseDTO;
import com.TreasureHunter.CommonLib.response.BaseResponse;
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
    public BaseResponse<UserResponseDTO> login(@Valid() @RequestBody() LoginRequestDTO request) {
        UserResponseDTO user = userService.login(request.getUsername(), request.getPassword());
        return new BaseResponse<>(
                CommonConstant.RESPONSE_CODE.SUCCESS,
                CommonConstant.RESPONSE_MESSAGE.SUCCESS,
                user
        );
    }
}
