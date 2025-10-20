package com.TreasureHunter.UserService.service;

import com.TreasureHunter.CommonLib.dto.request.auth.RegisterRequestDTO;
import com.TreasureHunter.CommonLib.dto.response.user.UserResponseDTO;

public interface UserService {
    void register(RegisterRequestDTO request);

    UserResponseDTO login(String username, String password);
}
