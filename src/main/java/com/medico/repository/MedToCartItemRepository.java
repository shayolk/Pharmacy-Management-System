package com.medico.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.medico.domain.MedToCartItem;
import com.medico.domain.CartItem;

@Transactional
public interface MedToCartItemRepository extends CrudRepository<MedToCartItem, Long> {
	void deleteByCartItem(CartItem cartItem);
}
