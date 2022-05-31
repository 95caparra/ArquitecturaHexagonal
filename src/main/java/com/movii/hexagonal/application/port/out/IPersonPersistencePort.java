package com.movii.hexagonal.application.port.out;

import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import com.movii.hexagonal.infraestructure.out.mysqldb.entities.PersonEntityMySQL;
import com.movii.hexagonal.infraestructure.out.mysqldb2.entities.CustomerEntityMySQL;

import java.util.Optional;

public interface IPersonPersistencePort {

    boolean savePersonMySQL(PersonEntityMySQL personEntity);

    boolean saveCustomerMySQL(CustomerEntityMySQL customerEntity);

    Optional<PersonEntityMySQL> findByIDMySQL(String id);

    boolean deletePersonMySQL(PersonEntityMySQL personEntity);

    boolean savePersonMongo(PersonEntityMongo personEntity);

    Optional<PersonEntityMongo> findByIDMongo(String id);

    boolean deletePersonMongo(PersonEntityMongo personEntity);

    boolean updatePersonMongo(PersonEntityMongo personEntity);

    boolean updatePersonMysql(PersonEntityMySQL personEntity);

}
