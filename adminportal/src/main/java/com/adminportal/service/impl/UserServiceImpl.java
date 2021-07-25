package com.adminportal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adminportal.domain.ShoppingCart;
import com.adminportal.domain.User;
import com.adminportal.domain.UserPayment;
import com.adminportal.domain.UserShipping;
import com.adminportal.domain.security.UserRole;
import com.adminportal.repository.RoleRepository;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.UserRoleService;
import com.adminportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRoleService userRoleService;

//	@Override
//	public User createUser(User user, Set<UserRole> userRoles) {
//		User localUser = userRepository.findByUsername(user.getUsername());
//
//		if (localUser != null) {
//			LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
//		} else {
//			for (UserRole ur : userRoles) {
//				roleRepository.save(ur.getRole());
//			}
//
//			user.getUserRoles().addAll(userRoles);
//
//			localUser = userRepository.save(user);
//		}
//
//		return localUser;
//	}
	
	@Override
	@Transactional
	public User createUser(User user, Set<UserRole> userRoles){
		User localUser = userRepository.findByUsername(user.getUsername());
		
		if(localUser != null) {
			LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			user.setShoppingCart(shoppingCart);
			
			user.setUserShippingList(new ArrayList<UserShipping>());
			user.setUserPaymentList(new ArrayList<UserPayment>());
			
			localUser = userRepository.save(user);
		}
		
		return localUser;
	}

	@Override
//	@Transactional
	public User saveUser(User user, Set<UserRole> userRoles) {
		
//		UserRole prevRole = new UserRole();
//		
//		Set<UserRole> prevUserRoles = user.getUserRoles();
//		for(UserRole ur : prevUserRoles) {
////			prevRole = ur;
//			userRoleService.removeOne(ur.getUserRoleId());
//		}
//		
//		UserRole newRole = new UserRole();
//		
//		for (UserRole ur : userRoles) {
////			newRole = ur;
//			roleRepository.save(ur.getRole());
//			userRoleService.save(new UserRole(user, ur.getRole()));
//		}
//
//		user.getUserRoles().clear();
//		user.setUserRoles(userRoles);
////		user.getUserRoles().addAll(userRoles);
		
		return userRepository.save(user);
	}

	@Override
	public User findOne(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public void removeOne(long id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
	
	@Override
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}
	
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

}
