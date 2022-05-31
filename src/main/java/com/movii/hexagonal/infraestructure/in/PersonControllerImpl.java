package com.movii.hexagonal.infraestructure.in;

import com.movii.hexagonal.application.port.in.*;
import com.movii.hexagonal.application.service.UpdatePersonMongoService;
import com.movii.hexagonal.commons.statuscode.StatusCodeConfig;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.dto.ResponseDTO;
import com.movii.hexagonal.exceptions.ExceptionWrapper;
import com.movii.hexagonal.infraestructure.in.controller.PersonController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PersonControllerImpl extends ExceptionWrapper implements PersonController{

    private final CreatePersonMySQLUseCase createPersonUseCase;

    private final DeletePersonMySQLUseCase deletePersonUseCase;

    private final UpdatePersonMongoService updatePersonUseCase;

    private final FindPersonMongoUseCase findPersonUseCase;
    private StatusCodeConfig statusCodeConfig;


    @Override
    public ResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(createPersonUseCase.createPerson(personDTO));
        responseDTO.setCode("200");
        responseDTO.setMessage("guardado!");

        return responseDTO;
    }

    @Override
    public ResponseDTO deletePerson(@PathVariable @Valid String id) {
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setData(deletePersonUseCase.deletePerson(id));
        responseDTO.setCode("200");
        responseDTO.setMessage("eliminado!");

        return responseDTO;
    }

    @Override
    public ResponseDTO findPerson(@PathVariable @Valid String id) {
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setData(findPersonUseCase.findPerson(id));
        responseDTO.setCode("200");
        responseDTO.setMessage("consultado!");

        return responseDTO;
    }

    @Override
    public ResponseDTO updatePerson(@RequestBody @Valid PersonDTO personDTO,
                                    @PathVariable @Valid String id) {
        ResponseDTO responseDTO = new ResponseDTO();

        responseDTO.setData(updatePersonUseCase.updatePerson(personDTO, id));
        responseDTO.setCode("200");
        responseDTO.setMessage("actualizado!");

        return responseDTO;
    }
}
