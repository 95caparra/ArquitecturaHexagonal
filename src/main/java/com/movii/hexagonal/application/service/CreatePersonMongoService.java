package com.movii.hexagonal.application.service;

import com.movii.hexagonal.application.port.in.CreatePersonMongoUseCase;
import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import com.movii.hexagonal.domain.entities.Person;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import com.movii.hexagonal.mappers.IPersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePersonMongoService implements CreatePersonMongoUseCase {

    private final IPersonPersistencePort iPersonPersistencePort;

    @Autowired
    RabbitTemplate template;
    @Override
    public boolean createPerson(PersonDTO personDTO)  {

        //Mapeo de Objeto de respuesta
        Person person = IPersonMapper.INSTANCE.personDTOToPerson(personDTO);

        PersonEntityMongo personEntityMongo = IPersonMapper.INSTANCE.personToPersonEntityMongo(person);

        iPersonPersistencePort.savePersonMongo(personEntityMongo);


        return true;
    }
}
