package com.movii.hexagonal.application.port.in;

import com.movii.hexagonal.dto.PersonDTO;

public interface UpdatePersonMongoUseCase {

    boolean updatePerson(PersonDTO personDTO, String id);

}
