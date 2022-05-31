package com.movii.hexagonal.infraestructure.in;

import com.movii.hexagonal.application.port.in.CreateCustomerMySQLUseCase;
import com.movii.hexagonal.application.port.in.CreatePersonMySQLUseCase;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.dto.ResponseDTO;
import com.movii.hexagonal.exceptions.ExceptionWrapper;
import com.movii.hexagonal.infraestructure.in.controller.CustomerController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CustomerControllerImpl extends ExceptionWrapper implements CustomerController {

    private final CreateCustomerMySQLUseCase createCustomerMySQLUseCase;

    @Override
    public ResponseDTO createCustomer(@RequestBody @Valid PersonDTO personDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(createCustomerMySQLUseCase.createCustomer(personDTO));
        responseDTO.setCode("200");
        responseDTO.setMessage("guardado!");

        return responseDTO;
    }
}
