package com.movii.hexagonal.exceptions.customExceptions;


import com.movii.hexagonal.commons.enums.ErrorCodeSource;
import com.movii.hexagonal.commons.enums.ErrorType;
import com.movii.hexagonal.commons.statuscode.StatusCode;
import org.springframework.http.HttpStatus;

public class CommunicationException extends ServiceException {

    public CommunicationException() {
        this(String.valueOf(HttpStatus.REQUEST_TIMEOUT.value()), HttpStatus.REQUEST_TIMEOUT.getReasonPhrase(), null);
    }

    public CommunicationException(String code, Throwable cause) {
        this(code, cause.getMessage(), cause);
    }

    public CommunicationException(String code, String message) {
        this(code, message, null);
    }

    public CommunicationException(String code, String message, Throwable cause) {
        super(ErrorType.COMMUNICATION, code, message, cause);
    }

    public CommunicationException(StatusCode statusCode) {
        this(statusCode, ErrorCodeSource.INTERNAL, null);
    }

    public CommunicationException(StatusCode statusCode, Throwable cause) {
        this(statusCode, ErrorCodeSource.INTERNAL, cause);
    }

    public CommunicationException(StatusCode statusCode, ErrorCodeSource codeSource, Throwable cause) {
        super(ErrorType.COMMUNICATION, statusCode, cause, codeSource);
    }

}
