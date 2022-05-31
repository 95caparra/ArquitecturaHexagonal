package com.movii.hexagonal.infraestructure.in.controller;

import com.movii.hexagonal.commons.helper.ConstantsHelper;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.dto.ResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(ConstantsHelper.PROJECT_PATH)
@ApiOperation(value = "Investor create", notes = "create a investor")
@ApiResponses({
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal server error Personalizado"),
        @ApiResponse(code = 401, message = "Mensaje Personalizado 1")
})
public interface CustomerController {

    @PostMapping("${spring.application.services.rest.createCustomer.path}")
    ResponseDTO createCustomer(PersonDTO personDTO);
}
