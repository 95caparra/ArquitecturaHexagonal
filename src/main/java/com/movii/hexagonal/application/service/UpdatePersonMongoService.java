package com.movii.hexagonal.application.service;

import com.movii.hexagonal.application.port.in.UpdatePersonMongoUseCase;
import com.movii.hexagonal.application.port.in.UpdatePersonMySQLUseCase;
import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import com.movii.hexagonal.config.MessagingConfig;
import com.movii.hexagonal.domain.entities.Person;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.dto.TemplateDTO;
import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import com.movii.hexagonal.mappers.IPersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePersonMongoService implements UpdatePersonMongoUseCase {

    private final IPersonPersistencePort iPersonPersistencePort;

    @Autowired
    RabbitTemplate template;

    @Override
    public boolean updatePerson(PersonDTO personDTO, String id) {
        Optional<PersonEntityMongo> entityMongo = iPersonPersistencePort.findByIDMongo(id);
        //Mapeo de Objeto de respuesta
        Person person = IPersonMapper.INSTANCE.personDTOToPerson(personDTO);

        PersonEntityMongo personMongo = IPersonMapper.INSTANCE.personToPersonEntityMongo(person);

        entityMongo.get().setNombre(personMongo.getNombre());
        entityMongo.get().setApellido(personMongo.getApellido());
        entityMongo.get().setCedula(personMongo.getCedula());

        if (iPersonPersistencePort.updatePersonMongo(entityMongo.get())){

            TemplateDTO templateDTO = new TemplateDTO();
            PersonDTO person2DTO = new PersonDTO();
            person2DTO.setId(id);
            person2DTO.setNombre(personMongo.getNombre());
            person2DTO.setApellido(personMongo.getApellido());
            person2DTO.setCedula(personMongo.getCedula());
            templateDTO.setPersonDTO(person2DTO);
            templateDTO.setAction("UPDATE-MONGO");

            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,
                    templateDTO);
        }

        return true;
    }
}
