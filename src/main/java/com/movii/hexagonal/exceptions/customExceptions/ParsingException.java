package com.movii.hexagonal.exceptions.customExceptions;

;
import com.movii.hexagonal.commons.enums.ErrorCodeSource;
import com.movii.hexagonal.commons.enums.ErrorType;
import com.movii.hexagonal.commons.statuscode.StatusCode;
import org.springframework.http.HttpStatus;

public class ParsingException extends ServiceException {

    public ParsingException() {
        this(String.valueOf(HttpStatus.UNPROCESSABLE_ENTITY.value()), HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), null);
    }

    public ParsingException(String code, Throwable cause) {
        this(code, cause.getMessage(), cause);
    }

    public ParsingException(String code, String message) {
        this(code, message, null);
    }

    public ParsingException(String code, String message, Throwable cause) {
        super(ErrorType.PARSING, code, message, cause);
    }

    public ParsingException(StatusCode statusCode) {
        this(statusCode, ErrorCodeSource.INTERNAL, null);
    }

    public ParsingException(StatusCode statusCode, Throwable cause) {
        this(statusCode, ErrorCodeSource.INTERNAL, cause);
    }

    public ParsingException(StatusCode statusCode, ErrorCodeSource codeSource, Throwable cause) {
        super(ErrorType.PARSING, statusCode, cause, codeSource);
    }

}
