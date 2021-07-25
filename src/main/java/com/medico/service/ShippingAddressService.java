package com.medico.service;

import com.medico.domain.ShippingAddress;
import com.medico.domain.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
}
