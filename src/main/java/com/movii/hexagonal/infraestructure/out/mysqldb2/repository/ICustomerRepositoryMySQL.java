package com.movii.hexagonal.infraestructure.out.mysqldb2.repository;

import com.movii.hexagonal.infraestructure.out.mysqldb2.entities.CustomerEntityMySQL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface ICustomerRepositoryMySQL extends CrudRepository<CustomerEntityMySQL, String>, Serializable {
}
