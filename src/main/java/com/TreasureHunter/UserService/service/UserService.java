package com.TreasureHunter.UserService.service;


import com.TreasureHunter.UserService.pojo.dto.request.auth.RegisterRequestDTO;
import com.TreasureHunter.UserService.pojo.dto.response.auth.LoginResponseDTO;

public interface UserService {
    void register(RegisterRequestDTO request);

    LoginResponseDTO login(String username, String password);
}
