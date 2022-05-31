package com.movii.hexagonal.exceptions;


import com.movii.hexagonal.commons.statuscode.StatusCodeConfig;
import com.movii.hexagonal.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.Optional;

@Service
public class ControlErrorService {

    public static final String PATTERN_SSS = "%s%s%s";
    @Autowired
    StatusCodeConfig statusCodeConfig;

    public ResponseDTO getErrorValidation(BindingResult ex) {
        ResponseDTO responseDTO = new ResponseDTO();

        Optional<ObjectError> errorLongitud = ex.getAllErrors().stream()
                .filter(filterLength ->
                        filterLength.getCode().equals("Length")
                                || filterLength.getCode().equals("Digits")
                                || filterLength.getCode().equals("DecimalMax")).findFirst();

        Optional<ObjectError> invalidValue = ex.getAllErrors().stream()
                .filter(filterLength ->
                        filterLength.getCode().equals("Pattern")
                                || filterLength.getCode().equals("Min")
                                || filterLength.getCode().equals("Max")
                                || filterLength.getCode().equals("typeMismatch")).findFirst();

        //Example custom Validator
        Optional<ObjectError> errorCustom = ex.getAllErrors().stream()
                .filter(filterLength ->
                        filterLength.getCode().equals("EstadosDTOValidator")
                                || filterLength.getCode().equals("GenericDTOValidator")
                                || filterLength.getCode().equals("ParametrosDTOValidator")).findFirst();

        FieldError validValue = (FieldError) invalidValue.get();
        FieldError validErrorLongitud = (FieldError) errorLongitud.get();
        FieldError validErrorCustom = (FieldError) errorCustom.get();
        if (errorLongitud.isPresent()) {

            //Validación longitud numerica y alfabetica
            responseDTO.setCode(statusCodeConfig.of("401").getCode());
            responseDTO.setMessage(String.format(PATTERN_SSS, statusCodeConfig.of("401").getMessage(), " : ", validValue.getField()));
        } else if (invalidValue.isPresent()) {

            //
            responseDTO.setCode(statusCodeConfig.of("402").getCode());
            responseDTO.setMessage(String.format(PATTERN_SSS, statusCodeConfig.of("402").getMessage(), " : ", validErrorLongitud.getField()));
        } else if (errorCustom.isPresent()) {

            responseDTO.setCode(statusCodeConfig.of("403").getCode());
            responseDTO.setMessage(String.format(PATTERN_SSS,statusCodeConfig.of("403").getMessage(), " : ", validErrorCustom.getField()));
        } else {

            Optional<ObjectError> errorObligatoriedad = ex.getAllErrors().stream().findFirst();
            responseDTO.setCode(statusCodeConfig.of("404").getCode());
            responseDTO.setMessage(String.format(PATTERN_SSS, statusCodeConfig.of("404").getMessage(), errorObligatoriedad.map(objectError -> ((FieldError) objectError).getField()).orElse("missingField")));
        }

        return responseDTO;
    }

}
