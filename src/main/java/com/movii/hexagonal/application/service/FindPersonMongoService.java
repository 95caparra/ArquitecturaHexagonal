package com.movii.hexagonal.application.service;

import com.movii.hexagonal.application.port.in.FindPersonMongoUseCase;
import com.movii.hexagonal.application.port.in.FindPersonMySQLUseCase;
import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import com.movii.hexagonal.config.MessagingConfig;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.dto.TemplateDTO;
import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindPersonMongoService implements FindPersonMongoUseCase {

    private final IPersonPersistencePort iPersonPersistencePort;

    @Autowired
    RabbitTemplate template;

    @Override
    public Optional<PersonEntityMongo> findPerson(String id) {

        Optional<PersonEntityMongo> entity = iPersonPersistencePort.findByIDMongo(id);

        TemplateDTO templateDTO = new TemplateDTO();
        PersonDTO person2DTO = new PersonDTO();
        person2DTO.setId(id);
        person2DTO.setNombre(entity.get().getNombre());
        person2DTO.setApellido(entity.get().getApellido());
        person2DTO.setCedula(entity.get().getCedula());
        templateDTO.setPersonDTO(person2DTO);
        templateDTO.setAction("FIND-MONGO");

        template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,
                templateDTO);

        return entity;
    }
}
