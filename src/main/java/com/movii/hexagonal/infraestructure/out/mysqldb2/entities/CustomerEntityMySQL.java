package com.movii.hexagonal.infraestructure.out.mysqldb2.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_moviired", schema="ejercicio")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerEntityMySQL implements Serializable {

    @Id
    @Builder.Default
    @Column(nullable = false, unique = true)
    private String id = UUID.randomUUID().toString();

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "apellido", nullable = false, unique = true)
    private String apellido;

    @Column(name = "cedula", nullable = false, unique = true)
    private String cedula;

}

