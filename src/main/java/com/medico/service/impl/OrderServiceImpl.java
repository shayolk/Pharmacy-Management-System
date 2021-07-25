package com.medico.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medico.domain.BillingAddress;
import com.medico.domain.CartItem;
import com.medico.domain.Med;
import com.medico.domain.Order;
import com.medico.domain.Payment;
import com.medico.domain.ShippingAddress;
import com.medico.domain.ShoppingCart;
import com.medico.domain.User;
import com.medico.repository.OrderRepository;
import com.medico.service.CartItemService;
import com.medico.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;
	
	public synchronized Order createOrder(ShoppingCart shoppingCart,
			ShippingAddress shippingAddress,
			BillingAddress billingAddress,
			Payment payment,
			String shippingMethod,
			LocalDate estimatedDeliveryDate,
			User user) {
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			Med med = cartItem.getMed();
			cartItem.setOrder(order);
			med.setInStockNumber(med.getInStockNumber() - cartItem.getQty());
		}
		
		Date shippingDate = Date.valueOf(estimatedDeliveryDate);
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		order.setShippingDate(shippingDate);
		shippingAddress.setOrder(order);
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}
	
	public Order findOne(Long id) {
		return orderRepository.findById(id).get();
	}

}
