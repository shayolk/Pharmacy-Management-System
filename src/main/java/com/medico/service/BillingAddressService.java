package com.medico.service;

import com.medico.domain.BillingAddress;
import com.medico.domain.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);
}
