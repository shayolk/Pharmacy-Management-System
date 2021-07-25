package com.medico.repository;

import org.springframework.data.repository.CrudRepository;

import com.medico.domain.UserPayment;

public interface UserPaymentRepository extends CrudRepository<UserPayment, Long>{

}
