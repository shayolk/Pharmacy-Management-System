package com.adminportal.service;

import com.adminportal.domain.User;
import com.adminportal.domain.security.UserRole;

public interface UserRoleService {
	
	void removeOne(Long id);

	UserRole save(UserRole userRole);
}
