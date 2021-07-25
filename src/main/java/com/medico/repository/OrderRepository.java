package com.medico.repository;

import org.springframework.data.repository.CrudRepository;

import com.medico.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
