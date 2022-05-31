package com.movii.hexagonal.application.service;

import com.movii.hexagonal.application.port.in.UpdatePersonMySQLUseCase;
import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import com.movii.hexagonal.domain.entities.Person;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import com.movii.hexagonal.infraestructure.out.mysqldb.entities.PersonEntityMySQL;
import com.movii.hexagonal.mappers.IPersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePersonMySQLService implements UpdatePersonMySQLUseCase {

    private final IPersonPersistencePort iPersonPersistencePort;

    @Override
    public boolean updatePerson(PersonDTO personDTO, String id) {
        Optional<PersonEntityMySQL> entity = iPersonPersistencePort.findByIDMySQL(id);
        //Mapeo de Objeto de respuesta
        Person person = IPersonMapper.INSTANCE.personDTOToPerson(personDTO);

        PersonEntityMySQL personMysql = IPersonMapper.INSTANCE.personToPersonEntityMysql(person);

        entity.get().setNombre(personMysql.getNombre());
        entity.get().setApellido(personMysql.getApellido());
        entity.get().setCedula(personMysql.getCedula());

        iPersonPersistencePort.updatePersonMysql(entity.get());
        return true;
    }
}
