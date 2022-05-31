package com.movii.hexagonal.application.port.in;

import com.movii.hexagonal.dto.PersonDTO;

public interface CreateCustomerMySQLUseCase {

    boolean createCustomer(PersonDTO personDTO);
}
