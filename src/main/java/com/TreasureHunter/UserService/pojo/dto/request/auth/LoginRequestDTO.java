package com.TreasureHunter.UserService.pojo.dto.request.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @NotNull()
    private String username;

    @NotNull()
    private String password;
}
