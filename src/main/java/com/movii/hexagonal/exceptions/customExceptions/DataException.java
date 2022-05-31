package com.movii.hexagonal.exceptions.customExceptions;


import com.movii.hexagonal.commons.enums.ErrorCodeSource;
import com.movii.hexagonal.commons.enums.ErrorType;
import com.movii.hexagonal.commons.statuscode.StatusCode;
import org.springframework.http.HttpStatus;

public class DataException extends ServiceException {

    public DataException() {
        this(String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST.getReasonPhrase(), null);
    }

    public DataException(String code, Throwable cause) {
        this(code, cause.getMessage(), cause);
    }

    public DataException(String code, String message) {
        this(code, message, null);
    }

    public DataException(String code, String message, Throwable cause) {
        super(ErrorType.DATA, code, message, cause);
    }

    public DataException(StatusCode statusCode) {
        this(statusCode, ErrorCodeSource.INTERNAL, null);
    }

    public DataException(StatusCode statusCode, Throwable cause) {
        this(statusCode, ErrorCodeSource.INTERNAL, cause);
    }

    public DataException(StatusCode statusCode, ErrorCodeSource codeSource, Throwable cause) {
        super(ErrorType.DATA, statusCode, cause, codeSource);
    }

}
