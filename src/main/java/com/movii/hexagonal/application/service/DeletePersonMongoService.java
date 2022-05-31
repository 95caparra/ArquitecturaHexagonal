package com.movii.hexagonal.application.service;

import com.movii.hexagonal.application.port.in.DeletePersonMongoUseCase;
import com.movii.hexagonal.application.port.in.DeletePersonMySQLUseCase;
import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePersonMongoService implements DeletePersonMongoUseCase {
    private final IPersonPersistencePort iPersonPersistencePort;

    @Override
    public boolean deletePerson(String id) {
        iPersonPersistencePort.deletePersonMongo(iPersonPersistencePort.findByIDMongo(id).get());
        return true;
    }
}
