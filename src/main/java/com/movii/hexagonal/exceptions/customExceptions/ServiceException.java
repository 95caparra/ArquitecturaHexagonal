package com.movii.hexagonal.exceptions.customExceptions;


import com.movii.hexagonal.commons.enums.ErrorCodeSource;
import com.movii.hexagonal.commons.enums.ErrorType;
import com.movii.hexagonal.commons.statuscode.StatusCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ServiceException extends Throwable {

    private final ErrorType errorType;
    private final String code;
    private final Object body;
    private HttpStatus httpStatus;

    public ServiceException(ErrorType errorType, String code, String message) {
        this(errorType, code, message, null, null, null, Boolean.TRUE, Boolean.FALSE);
    }

    public ServiceException(ErrorType errorType, String code, String message, Throwable cause) {
        this(errorType, code, message, null, cause, null, Boolean.TRUE, Boolean.FALSE);
    }

    public ServiceException(ErrorType errorType, String message, HttpStatus httpStatus, Object body) {
        this(errorType, null, message, body, null, httpStatus, Boolean.TRUE, Boolean.FALSE);
    }

    protected ServiceException(ErrorType errorType, String code, String message, Object body,
                               Throwable cause,
                               HttpStatus httpStatus,
                               boolean enableSuppression,
                               boolean writableStackTrace) {

        super(message, cause, enableSuppression, writableStackTrace);
        this.errorType = errorType;
        this.code = code;
        this.body = body;
        this.httpStatus = httpStatus;
    }

    public ServiceException(ErrorType errorType, StatusCode statusCode, Throwable cause) {
        this(errorType, statusCode, cause, ErrorCodeSource.INTERNAL, Boolean.TRUE, Boolean.FALSE);
    }

    public ServiceException(ErrorType errorType, StatusCode statusCode, Throwable cause, ErrorCodeSource codeSource) {
        this(errorType, statusCode, cause, codeSource, Boolean.TRUE, Boolean.FALSE);
    }

    protected ServiceException(ErrorType errorType, StatusCode statusCode,
                               Throwable cause, ErrorCodeSource codeSource,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(statusCode.getMessage(), cause, enableSuppression, writableStackTrace);

        this.errorType = errorType;
        this.body = null;
        this.code = ErrorCodeSource.EXTERNAL.equals(codeSource) ? statusCode.getExtCode() : statusCode.getCode();
    }

}
