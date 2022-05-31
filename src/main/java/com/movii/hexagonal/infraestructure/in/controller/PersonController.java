package com.movii.hexagonal.infraestructure.in.controller;

import com.movii.hexagonal.commons.helper.ConstantsHelper;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.dto.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RequestMapping(ConstantsHelper.PROJECT_PATH)
@ApiOperation(value = "Investor create", notes = "create a investor")
@ApiResponses({
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal server error Personalizado"),
        @ApiResponse(code = 401, message = "Mensaje Personalizado 1")
})
public interface PersonController {

    @PostMapping("${spring.application.services.rest.createPerson.path}")
    ResponseDTO createPerson(PersonDTO personDTO);

    @DeleteMapping("${spring.application.services.rest.deletePerson.path}")
    ResponseDTO deletePerson(String id);

    @GetMapping("${spring.application.services.rest.getPerson.path}")
    ResponseDTO findPerson(String id);

    @PutMapping("${spring.application.services.rest.updatePerson.path}")
    ResponseDTO updatePerson(PersonDTO personDTO, String id);

}
