package com.TreasureHunter.UserService.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse<T> {
    private String code;
    private String message;
    private T data;

    public BaseResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResponse(String status, String message) {
        this(status, message, null);
    }
}
