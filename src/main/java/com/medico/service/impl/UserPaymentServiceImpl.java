package com.medico.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medico.domain.UserPayment;
import com.medico.repository.UserPaymentRepository;
import com.medico.service.UserPaymentService;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{

	@Autowired
	private UserPaymentRepository userPaymentRepository;
		
	public UserPayment findById(Long id) {
		return userPaymentRepository.findById(id).get();
	}

	@Override
	public void removeById(Long id) {
		userPaymentRepository.deleteById(id);
	}
} 
