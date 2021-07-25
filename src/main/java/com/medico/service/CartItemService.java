package com.medico.service;

import java.util.List;

import com.medico.domain.Med;
import com.medico.domain.CartItem;
import com.medico.domain.Order;
import com.medico.domain.ShoppingCart;
import com.medico.domain.User;

public interface CartItemService {
	List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
	
	CartItem updateCartItem(CartItem cartItem);
	
	CartItem addMedToCartItem(Med med, User user, int qty);
	
	CartItem findById(Long id);
	
	void removeCartItem(CartItem cartItem);
	
	CartItem save(CartItem cartItem);
	
	List<CartItem> findByOrder(Order order);
}
