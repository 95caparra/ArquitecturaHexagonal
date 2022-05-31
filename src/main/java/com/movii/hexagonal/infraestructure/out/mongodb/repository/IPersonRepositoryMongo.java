package com.movii.hexagonal.infraestructure.out.mongodb.repository;

import com.movii.hexagonal.infraestructure.out.mongodb.entities.PersonEntityMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public interface IPersonRepositoryMongo extends MongoRepository<PersonEntityMongo, String> {
}
