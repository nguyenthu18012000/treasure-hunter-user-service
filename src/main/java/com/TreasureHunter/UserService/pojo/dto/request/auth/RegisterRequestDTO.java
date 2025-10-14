package com.TreasureHunter.UserService.pojo.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequestDTO {
    @NotNull()
    private String username;

    @NotNull()
    private String password;
}
