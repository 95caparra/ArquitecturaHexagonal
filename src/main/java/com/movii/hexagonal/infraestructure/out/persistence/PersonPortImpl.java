package com.movii.hexagonal.infraestructure.out.persistence;

import com.movii.hexagonal.application.port.out.IPersonPersistencePort;
import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import com.movii.hexagonal.infraestructure.out.mongodb.repository.IPersonRepositoryMongo;
import com.movii.hexagonal.infraestructure.out.mysqldb.entities.PersonEntityMySQL;
import com.movii.hexagonal.infraestructure.out.mysqldb.repository.IPersonRepositoryMySQL;
import com.movii.hexagonal.infraestructure.out.mysqldb2.entities.CustomerEntityMySQL;
import com.movii.hexagonal.infraestructure.out.mysqldb2.repository.ICustomerRepositoryMySQL;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonPortImpl implements IPersonPersistencePort {

    private final IPersonRepositoryMySQL iPersonRepositoryMysql;

    private final ICustomerRepositoryMySQL iCustomerRepositoryMySQL;

    private final IPersonRepositoryMongo iPersonRepositoryMongo;

    public PersonPortImpl(IPersonRepositoryMySQL iPersonRepositoryMysql,
                          ICustomerRepositoryMySQL iCustomerRepositoryMySQL,
                          IPersonRepositoryMongo iPersonRepositoryMongo) {
        this.iPersonRepositoryMysql = iPersonRepositoryMysql;
        this.iCustomerRepositoryMySQL = iCustomerRepositoryMySQL;
        this.iPersonRepositoryMongo = iPersonRepositoryMongo;
    }

    @Override
    public boolean savePersonMySQL(PersonEntityMySQL personEntity) {
        PersonEntityMySQL person = iPersonRepositoryMysql.save(personEntity);
        return true;
    }

    @Override
    public boolean saveCustomerMySQL(CustomerEntityMySQL customerEntity) {
        CustomerEntityMySQL customer = iCustomerRepositoryMySQL.save(customerEntity);
        return true;
    }


    @Override
    public Optional<PersonEntityMySQL> findByIDMySQL(String id) {
        Optional<PersonEntityMySQL> person = iPersonRepositoryMysql.findById(id);
        return person;
    }

    @Override
    public boolean deletePersonMySQL(PersonEntityMySQL personEntity) {
        iPersonRepositoryMysql.delete(personEntity);
        return true;
    }

    @Override
    public boolean savePersonMongo(PersonEntityMongo personEntity) {
        PersonEntityMongo person = iPersonRepositoryMongo.save(personEntity);
        return true;
    }

    @Override
    public Optional<PersonEntityMongo> findByIDMongo(String id) {
        Optional<PersonEntityMongo> person = iPersonRepositoryMongo.findById(id);
        return person;
    }

    @Override
    public boolean deletePersonMongo(PersonEntityMongo personEntity) {
        iPersonRepositoryMongo.delete(personEntity);
        return true;
    }

    @Override
    public boolean updatePersonMongo(PersonEntityMongo personEntity) {
        iPersonRepositoryMongo.save(personEntity);
        return true;
    }

    @Override
    public boolean updatePersonMysql(PersonEntityMySQL personEntity) {
        iPersonRepositoryMysql.save(personEntity);
        return true;
    }
}
