package com.movii.hexagonal.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    private String id = UUID.randomUUID().toString();
    private String nombre;
    private String apellido;
    private String cedula;
}
