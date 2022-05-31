package com.movii.hexagonal.application.service;

import com.movii.hexagonal.application.port.in.CreatePersonMySQLUseCase;
import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import com.movii.hexagonal.config.MessagingConfig;
import com.movii.hexagonal.domain.entities.Person;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.dto.TemplateDTO;
import com.movii.hexagonal.infraestructure.out.mysqldb.entities.PersonEntityMySQL;
import com.movii.hexagonal.mappers.IPersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePersonMySQLService implements CreatePersonMySQLUseCase {

    private final IPersonPersistencePort iPersonPersistencePort;

    @Autowired
    RabbitTemplate template;
    @Override
    public boolean createPerson(PersonDTO personDTO)  {

        //Mapeo de Objeto de respuesta
        Person person = IPersonMapper.INSTANCE.personDTOToPerson(personDTO);


        PersonEntityMySQL personEntityMysql = IPersonMapper.INSTANCE.personToPersonEntityMysql(person);

        if(iPersonPersistencePort.savePersonMySQL(personEntityMysql)){
            TemplateDTO templateDTO = new TemplateDTO();

            templateDTO.setPersonDTO(personDTO);
            templateDTO.setAction("CREATE-MYSQL");
            template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY,
                    templateDTO);
        }
        return true;
    }
}
