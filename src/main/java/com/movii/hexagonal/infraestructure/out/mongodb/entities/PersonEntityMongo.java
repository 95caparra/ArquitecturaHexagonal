package com.movii.hexagonal.infraestructure.out.mongodb.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person_moviired")
public class PersonEntityMongo {

    @Id
    private String id = UUID.randomUUID().toString();

    private String nombre;

    private String apellido;

    private String cedula;
}