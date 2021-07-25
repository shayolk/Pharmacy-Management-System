package com.medico.service;

import com.medico.domain.UserPayment;

public interface UserPaymentService {
	UserPayment findById(Long id);

	void removeById(Long id);
}
