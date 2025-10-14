package com.TreasureHunter.UserService.controller;

import com.TreasureHunter.UserService.constant.CommonConstant;
import com.TreasureHunter.UserService.pojo.dto.request.auth.LoginRequestDTO;
import com.TreasureHunter.UserService.pojo.dto.request.auth.RegisterRequestDTO;
import com.TreasureHunter.UserService.pojo.dto.response.auth.LoginResponseDTO;
import com.TreasureHunter.UserService.response.BaseResponse;
import com.TreasureHunter.UserService.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<BaseResponse<Void>> register(
            @Valid() @RequestBody() RegisterRequestDTO request
    ) {
        userService.register(request);
        BaseResponse<Void> response = new BaseResponse<>(
                CommonConstant.RESPONSE_CODE.SUCCESS,
                CommonConstant.RESPONSE_MESSAGE.SUCCESS,
                null
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginResponseDTO>> login(
            @Valid() @RequestBody() LoginRequestDTO request
    ) {
        LoginResponseDTO loginResponse = userService.login(request.getUsername(), request.getPassword());
        BaseResponse<LoginResponseDTO> response = new BaseResponse<>(
                CommonConstant.RESPONSE_CODE.SUCCESS,
                CommonConstant.RESPONSE_MESSAGE.SUCCESS,
                loginResponse
        );
        return ResponseEntity.ok(response);
    }
}
