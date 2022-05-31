package com.movii.hexagonal.mappers;

import com.movii.hexagonal.domain.entities.Person;
import com.movii.hexagonal.dto.PersonDTO;
import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import com.movii.hexagonal.infraestructure.out.mysqldb.entities.PersonEntityMySQL;
import com.movii.hexagonal.infraestructure.out.mysqldb2.entities.CustomerEntityMySQL;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IPersonMapper {

    IPersonMapper INSTANCE = Mappers.getMapper(IPersonMapper.class);

    @Mappings({
            @Mapping(target="nombre", source="nombre"),
            @Mapping(target="apellido", source="apellido"),
            @Mapping(target="cedula", source="cedula"),
    })
    PersonEntityMySQL personToPersonEntityMysql(Person person);

    @Mappings({
            @Mapping(target="nombre", source="nombre"),
            @Mapping(target="apellido", source="apellido"),
            @Mapping(target="cedula", source="cedula"),
    })
    CustomerEntityMySQL personToCustomerEntityMysql(Person person);

    @Mappings({
            @Mapping(target="nombre", source="nombre"),
            @Mapping(target="apellido", source="apellido"),
            @Mapping(target="cedula", source="cedula"),
    })
    PersonEntityMongo personToPersonEntityMongo(Person person);

    @Mappings({
            @Mapping(target="nombre", source="nombre"),
            @Mapping(target="apellido", source="apellido"),
            @Mapping(target="cedula", source="cedula"),
    })
    PersonDTO personToPersonDTO(Person person);

    @Mappings({
            @Mapping(target="nombre", source="nombre"),
            @Mapping(target="apellido", source="apellido"),
            @Mapping(target="cedula", source="cedula"),
    })
    Person personDTOToPerson(PersonDTO personDTO);
}
