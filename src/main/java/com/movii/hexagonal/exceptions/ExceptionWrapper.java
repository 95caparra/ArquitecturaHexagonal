package com.movii.hexagonal.exceptions;

import com.movii.hexagonal.commons.statuscode.StatusCodeConfig;
import com.movii.hexagonal.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionWrapper {


    public static final String SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA = "Se ha presentado el siguiente error: {} con la siguiente traza: {}";
    @Autowired
    ControlErrorService controlErrorService;
    @Autowired
    StatusCodeConfig statusCodeConfig;

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseDTO handleValidationExceptions(Exception ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        String uuid = generateUUID(ex);
        responseDTO.setCode(statusCodeConfig.of("406").getCode());
        responseDTO.setMessage(String.format(statusCodeConfig.of("406").getMessage(), uuid));
        responseDTO.setUuid(uuid);
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDTO handleJSONError(HttpMessageNotReadableException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        String uuid = generateUUID(ex);
        responseDTO.setCode(statusCodeConfig.of("406").getCode());
        responseDTO.setMessage(String.format(statusCodeConfig.of("406").getMessage(), uuid));
        responseDTO.setUuid(uuid);
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseDTO handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        String uuid = generateUUID(ex);
        responseDTO.setCode(statusCodeConfig.of("406").getCode());
        responseDTO.setMessage(String.format(statusCodeConfig.of("406").getMessage(), uuid));
        responseDTO.setUuid(uuid);
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    protected ResponseDTO handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        String uuid = generateUUID(ex);
        responseDTO.setCode(statusCodeConfig.of("406").getCode());
        responseDTO.setMessage(String.format(statusCodeConfig.of("406").getMessage(), uuid));
        responseDTO.setUuid(uuid);
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseDTO handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        String uuid = generateUUID(ex);
        responseDTO.setCode(statusCodeConfig.of("405").getCode());
        responseDTO.setMessage(String.format(statusCodeConfig.of("405").getMessage(), uuid));
        responseDTO.setUuid(uuid);
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseDTO handleConstraintViolationException(ConstraintViolationException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        String uuid = generateUUID(ex);
        responseDTO.setCode(statusCodeConfig.of("406").getCode());
        responseDTO.setMessage(String.format(statusCodeConfig.of("406").getMessage(), uuid));
        responseDTO.setUuid(uuid);
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseDTO handleAuthenticationException(AuthenticationException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        String uuid = generateUUID(ex);
        responseDTO.setCode(statusCodeConfig.of("407").getCode());
        responseDTO.setMessage(String.format(statusCodeConfig.of("407").getMessage(), uuid));
        responseDTO.setUuid(uuid);
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO handleValidationExceptions(MethodArgumentNotValidException ex) {
        ResponseDTO responseDTO = controlErrorService.getErrorValidation(ex.getBindingResult());
        responseDTO.setUuid(generateUUID(ex));
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseDTO handleValidationExceptions(BindException ex) {
        ResponseDTO responseDTO = controlErrorService.getErrorValidation(ex.getBindingResult());
        responseDTO.setUuid(generateUUID(ex));
        log.error(SE_HA_PRESENTADO_EL_SIGUIENTE_ERROR_CON_LA_SIGUIENTE_TRAZA, responseDTO.getMessage(), responseDTO.getMessage());
        return responseDTO;
    }

    public String generateUUID(Object exception) {
        String uuidError = java.util.UUID.randomUUID().toString();
        log.info(uuidError, exception);
        return uuidError;
    }
}
