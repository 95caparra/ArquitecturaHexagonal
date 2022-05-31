package com.movii.hexagonal.exceptions.customExceptions;

import com.movii.hexagonal.commons.enums.ErrorCodeSource;
import com.movii.hexagonal.commons.enums.ErrorType;
import com.movii.hexagonal.commons.statuscode.StatusCode;
import org.springframework.http.HttpStatus;

public class ProcessingException extends ServiceException {

    public ProcessingException() {
        this(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }

    public ProcessingException(String code, Throwable cause) {
        this(code, cause.getMessage(), cause);
    }

    public ProcessingException(String code, String message) {
        this(code, message, null);
    }

    public ProcessingException(String code, String message, Throwable cause) {
        super(ErrorType.PROCESSING, code, message, cause);
    }

    public ProcessingException(StatusCode statusCode) {
        this(statusCode, ErrorCodeSource.INTERNAL, null);
    }

    public ProcessingException(StatusCode statusCode, Throwable cause) {
        this(statusCode, ErrorCodeSource.INTERNAL, cause);
    }

    public ProcessingException(StatusCode statusCode, ErrorCodeSource codeSource, Throwable cause) {
        super(ErrorType.PROCESSING, statusCode, cause, codeSource);
    }

}
