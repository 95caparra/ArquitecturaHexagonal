package com.movii.hexagonal.application.port.in;

import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;

import java.util.Optional;

public interface FindPersonMongoUseCase {

    Optional<PersonEntityMongo> findPerson(String id);
}
