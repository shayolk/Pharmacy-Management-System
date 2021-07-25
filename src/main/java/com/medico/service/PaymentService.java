package com.medico.service;

import com.medico.domain.Payment;
import com.medico.domain.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
}
