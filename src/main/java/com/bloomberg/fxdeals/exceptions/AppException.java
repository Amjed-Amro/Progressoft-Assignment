package com.bloomberg.fxdeals.exceptions;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppException extends Exception {
    private String responseCode;
    private String causeLog;
    private String status;
    private Object response;

    public AppException(String responseCode, String causeLog, String status, String message, Throwable cause,
                        Object... args) {
        super(message, cause);
        this.responseCode = responseCode;
        this.causeLog = causeLog;
        this.status = status;
    }

    public AppException(String responseCode, String causeLog, String status) {
        this(responseCode, causeLog, status, null, null);
    }
}
