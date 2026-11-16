package com.game.user.service;

import com.game.common.dto.request.auth.RegisterRequestDTO;
import com.game.common.dto.response.user.UserResponseDTO;

public interface UserService {
    void register(RegisterRequestDTO request);

    UserResponseDTO login(String username, String password);
}
