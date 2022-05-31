package com.movii.hexagonal.application.service;

import com.movii.hexagonal.application.port.in.FindPersonMongoUseCase;
import com.movii.hexagonal.application.port.in.FindPersonMySQLUseCase;
import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import com.movii.hexagonal.infraestructure.out.mysqldb.entities.PersonEntityMySQL;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindPersonMySQLService implements FindPersonMySQLUseCase {

    private final IPersonPersistencePort iPersonPersistencePort;
    @Override
    public Optional<PersonEntityMySQL> findPerson(String id) {

        Optional<PersonEntityMySQL> entity = iPersonPersistencePort.findByIDMySQL(id);

        return entity;
    }
}
