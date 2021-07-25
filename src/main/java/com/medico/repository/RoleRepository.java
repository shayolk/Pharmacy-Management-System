package com.medico.repository;

import org.springframework.data.repository.CrudRepository;

import com.medico.domain.security.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
