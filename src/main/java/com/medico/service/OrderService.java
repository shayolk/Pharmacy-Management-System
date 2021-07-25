package com.medico.service;

import java.time.LocalDate;

import com.medico.domain.BillingAddress;
import com.medico.domain.Order;
import com.medico.domain.Payment;
import com.medico.domain.ShippingAddress;
import com.medico.domain.ShoppingCart;
import com.medico.domain.User;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			LocalDate estimatedDeliveryDate,
			User user);
	
	Order findOne(Long id);
}
