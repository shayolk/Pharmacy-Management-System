package com.adminportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adminportal.domain.User;
import com.adminportal.domain.security.UserRole;
import com.adminportal.repository.UserRoleRepository;
import com.adminportal.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	public void removeOne(Long id) {
		userRoleRepository.deleteById(id);
	}

	@Override
	public UserRole save(UserRole userRole) {
		return userRoleRepository.save(userRole);
	}
}
