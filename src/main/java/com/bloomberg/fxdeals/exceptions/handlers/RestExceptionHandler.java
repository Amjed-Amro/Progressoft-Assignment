package com.bloomberg.fxdeals.exceptions.handlers;

import com.bloomberg.fxdeals.constants.ApiConstants;
import com.bloomberg.fxdeals.exceptions.AppException;
import com.bloomberg.fxdeals.models.common.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Log4j2
public class RestExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        log.error("in handleInvalidArgument {}", ex.getMessage());
        return new ApiResponse().builder()
                .response(new ApiResponse.Response().builder()
                        .code(ApiConstants.RESPONSE.CODE.VALIDATION)
                        .message(ApiConstants.RESPONSE.MESSAGE.INVALID_REQUEST)
                        .status(ApiConstants.RESPONSE.STATUS.REJECTED)
                        .build())
                .body(errorMap)
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AppException.class)
    public ApiResponse handleAppException(AppException ex) {
        log.error("in handleAppException {}", ex.getMessage());
        return new ApiResponse().builder()
                .response(new ApiResponse.Response().builder()
                        .code(ex.getResponseCode())
                        .message(ex.getCauseLog())
                        .status(ex.getStatus())
                        .build())
                .build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception ex) {
        log.error("in handleException {}", ex.getMessage());
        return new ApiResponse().builder()
                .response(new ApiResponse.Response().builder()
                        .code(ApiConstants.RESPONSE.CODE.TECHNICAL)
                        .message(ApiConstants.RESPONSE.MESSAGE.TECHNICAL_SERVICE_ERROR)
                        .status(ApiConstants.RESPONSE.STATUS.FAILED)
                        .build())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataIntegrityViolationException.class, PSQLException.class})
    public ApiResponse handleDataIntegrityViolationException(Exception ex) {
        log.error("in DataIntegrityViolationException {}", ex.getMessage());
        return new ApiResponse().builder()
                .response(new ApiResponse.Response().builder()
                        .code(ApiConstants.RESPONSE.CODE.VALIDATION)
                        .message(ApiConstants.RESPONSE.MESSAGE.ALREADY_CONFIGURED)
                        .status(ApiConstants.RESPONSE.STATUS.REJECTED)
                        .build())
                .build();
    }


}
