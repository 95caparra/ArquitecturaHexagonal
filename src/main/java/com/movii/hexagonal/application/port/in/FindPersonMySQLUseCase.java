package com.movii.hexagonal.application.port.in;

import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import com.movii.hexagonal.infraestructure.out.mysqldb.entities.PersonEntityMySQL;

import java.util.Optional;

public interface FindPersonMySQLUseCase {

    Optional<PersonEntityMySQL> findPerson(String id);
}
