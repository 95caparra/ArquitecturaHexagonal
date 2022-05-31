package com.movii.hexagonal.application.service;

import com.movii.hexagonal.application.port.in.DeletePersonMongoUseCase;
import com.movii.hexagonal.application.port.in.DeletePersonMySQLUseCase;
import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import com.movii.hexagonal.config.MessagingConfig;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.dto.TemplateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletePersonMySQLService implements DeletePersonMySQLUseCase {
    private final IPersonPersistencePort iPersonPersistencePort;

    @Autowired
    RabbitTemplate template;
    @Override
    public boolean deletePerson(String id) {
        if (iPersonPersistencePort.deletePersonMySQL(iPersonPersistencePort.findByIDMySQL(id).get())){
            TemplateDTO templateDTO = new TemplateDTO();
            PersonDTO personDTO = new PersonDTO();
            personDTO.setId(id);
            templateDTO.setPersonDTO(personDTO);
            templateDTO.setAction("DELETE-MYSQL");

            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,
                    templateDTO);
        }
        return true;
    }
}
