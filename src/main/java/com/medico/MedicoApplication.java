package com.medico;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.medico.domain.User;
import com.medico.domain.security.Role;
import com.medico.domain.security.UserRole;
import com.medico.service.UserService;
import com.medico.utility.SecurityUtility;

@SpringBootApplication
public class MedicoApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MedicoApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("Shayak");
		user1.setLastName("Das");
		user1.setUsername("root");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("root"));
		user1.setEmail("shayakdas.cse18@itbhu.ac.in");
		Set<UserRole> userRoles = new HashSet<>();
//		Role role1= new Role(1, "ROLE_USER");
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));
		
		userService.createUser(user1, userRoles);
	}


}
