package com.medico.repository;

import org.springframework.data.repository.CrudRepository;

import com.medico.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	
	User findByEmail(String email);
}
