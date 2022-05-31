package com.movii.hexagonal.exceptions;

public enum StatusMessages {


    HTTP_OK(200,"Ejecución Exitosa"),
    HTTP_DELETE_OK(200,"Borrado Satisfactoriamente"),
    HTTP_BAD_REQUEST(400,"Los parametros ingresados nos son validos"),
    HTTP_BAD_REQUEST_EMPTY_DATASET(400,"No se encontraron resultados asociados al ID ingresado"),
    HTTP_BAD_REQUEST_DATA_INTEGRITY_VIOLATION(400,"Se ha violado una regla de integridad de la base de datos."),
    HTTP_BAD_REQUEST_IDENTIFIER_GENERATION(400,"No se puede crear un objeto sin la llave primaria."),
    HTTP_BAD_REQUEST_JSON_STRUCTURE(400,"El JSON ingresado tiene problemas de estructura"),
    HTTP_BAD_REQUEST_TITLE(400,"El parametro titulo no puede estar vacio"),
    HTTP_STATUS_NOT_FOUND(404,"No se obtuvieron resultados asociados a la busqueda"),
    HTTP_UNAUTHORIZED(401,"No autorizado, error de signature");

    private final int statusCode;
    private final String descriptionCode;

    private StatusMessages (int statusCode, String descriptionCode){
        this.statusCode = statusCode;
        this.descriptionCode = descriptionCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getDescriptionCode() {
        return descriptionCode;
    }
}