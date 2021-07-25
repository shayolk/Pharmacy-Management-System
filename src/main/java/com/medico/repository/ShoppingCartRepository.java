package com.medico.repository;

import org.springframework.data.repository.CrudRepository;

import com.medico.domain.ShoppingCart;

public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
