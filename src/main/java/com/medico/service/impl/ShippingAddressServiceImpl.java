package com.medico.service.impl;

import org.springframework.stereotype.Service;

import com.medico.domain.ShippingAddress;
import com.medico.domain.UserShipping;
import com.medico.service.ShippingAddressService;

@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
	public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
		shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
		shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
		shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
		shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
		shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
		shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
		shippingAddress.setShippingAddressZipcode(userShipping.getUserShippingZipcode());
		
		return shippingAddress;
	}
}
