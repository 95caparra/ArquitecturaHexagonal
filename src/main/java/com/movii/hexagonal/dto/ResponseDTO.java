package com.movii.hexagonal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Tag(name = "CustomResponse", description = "Objeto de respuesta estandar")
public final class ResponseDTO {

    @Schema(description = "Codigo de respuesta de la solicitud")
    private String code;

    @Schema(description = "Mensaje que confirma o rechaza la solicitud")
    private String message;

    @Schema(description = "Objeto que contienen datos relevantes a responder")
    private Object data;

    @Schema(description = "Identificador unico de Transacción")
    private String uuid;

}
