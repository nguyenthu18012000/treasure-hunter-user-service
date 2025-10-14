package com.TreasureHunter.UserService.exception;

import com.TreasureHunter.UserService.response.BaseResponse;
import io.micrometer.common.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    protected static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({CommonException.class})
    public ResponseEntity<BaseResponse<?>> handleCustomException(CommonException ex, final Throwable throwable) {
        BaseResponse<?> apiResponse = new BaseResponse<>(ex.getErrorCode(), ex.getMessage());

        Throwable rootCause;
        for (rootCause = throwable; rootCause.getCause() != null && rootCause.getCause() != rootCause; rootCause = rootCause.getCause()) {
        }

        String logMessage = StringUtils.isEmpty(throwable.getMessage()) ? ex.getMessage() : throwable.getMessage();
        String className = rootCause.getStackTrace()[0].getClassName();
        logger.error("Class: {}, Method: {} Error: {}", className, rootCause.getStackTrace()[0].getMethodName(), logMessage);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errors);
    }

}
