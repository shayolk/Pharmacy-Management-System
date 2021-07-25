package com.adminportal.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.adminportal.domain.User;
import com.adminportal.domain.security.Role;
import com.adminportal.domain.security.UserRole;
import com.adminportal.repository.UserRepository;
import com.adminportal.service.UserService;
import com.adminportal.utility.SecurityUtility;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUser(Model model) {
		User user = new User();
		Role role = new Role();
		
		role.setName("ROLE_USER");
		
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		return "addUser";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUserPost(
			Model model,
			@ModelAttribute("user") User user, 
			@ModelAttribute("role") Role role, 
			@ModelAttribute("password") String password, 
			HttpServletRequest request
			) throws Exception  {
		if (userService.findByUsername(user.getUsername()) != null) {
			model.addAttribute("usernameExists", true);
			
			return "addUser";
		}
		
		if (userService.findByEmail(user.getEmail()) != null) {
			model.addAttribute("emailExists", true);
			
			return "addUser";
		}
		
		Set<UserRole> userRoles = new HashSet<>();
		
		user.setPassword(SecurityUtility.passwordEncoder().encode(password));
		role.setRoleId(role.getName()=="ROLE_ADMIN"?0:1);

		userRoles.add(new UserRole(user, role));
		userService.createUser(user, userRoles);
		
		return "redirect:userList";
	}
	
	@RequestMapping("/userInfo")
	public String userInfo(@RequestParam("id") Long id, Model model) {
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		
		return "userInfo";
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(@RequestParam("id") Long id, Model model) {
		User user = userService.findOne(id);
		Role role = new Role();
		
		Set<UserRole> userRoles = user.getUserRoles();
		for(UserRole userRole : userRoles) {
			role = userRole.getRole();
		}
		
		model.addAttribute("user", user);
		model.addAttribute("role", role);
		
		return "updateUser";
		
//		String username = user.getUsername();
//		String firstName = user.getFirstName();
//		String lastName = user.getLastName();
//		String email = user.getEmail();
//		String phone = user.getPhone();
//		boolean enabled = user.isEnabled();
//		
//		String role = "ROLE_USER";
//		Set<UserRole> userRoles = user.getUserRoles();
//		
//		for(UserRole userRole : userRoles) {
//			role = userRole.getRole().getName();
//		}
//		
//		model.addAttribute("id", id);
//		model.addAttribute("username", username);
//		model.addAttribute("firstName", firstName);
//		model.addAttribute("lastName", lastName);
//		model.addAttribute("email", email);
//		model.addAttribute("phone", phone);
//		model.addAttribute("enabled", enabled);
//		model.addAttribute("role", role);
//		
//		return "updateUser";
	}
	
	@RequestMapping(value="/updateUser", method=RequestMethod.POST)
	public String updateUserPost(
			Model model, 
			@ModelAttribute("user") User user,
			@ModelAttribute("role") Role role,
//			@RequestParam("id") Long id,
//			@ModelAttribute("username") String username,
//			@ModelAttribute("firstName") String firstName,
//			@ModelAttribute("lastName") String lastName,
//			@ModelAttribute("email") String email,
//			@ModelAttribute("phone") String phone,
//			@ModelAttribute("enabled") boolean enabled,
//			@ModelAttribute("role") String role,
//			@ModelAttribute("password") String password, 
			HttpServletRequest request
			) {
		
//		User user = userService.findOne(id);
//		
//		user.setUsername(username);
//		user.setFirstName(firstName);
//		user.setLastName(lastName);
//		user.setPhone(phone);
//		user.setEnabled(enabled);
		
		if (userService.findByUsername(user.getUsername()) != null 
				&& userService.findByUsername(user.getUsername()).getId() != user.getId()) {
			model.addAttribute("usernameExists", true);
			
			return "updateUser";
		}
		
		if(user.getPassword() == null) {
			user.setPassword(userService.findOne(user.getId()).getPassword());
			model.addAttribute("passwordEmpty", true);
			
			return "updateUser";
		}
		
//		if(password != null) {
//			user.setPassword(SecurityUtility.passwordEncoder().encode(password));
//		}
		
		if(user.getPassword() != userService.findOne(user.getId()).getPassword()) {
			user.setPassword(SecurityUtility.passwordEncoder().encode(user.getPassword()));
		}
		
//		Set<UserRole> userRoles = new HashSet<>();
//		role.setRoleId(role.getName()=="ROLE_ADMIN"?0:1);
//
//		userRoles.add(new UserRole(user, role));
//		userService.saveUser(user, userRoles);
		
		userService.save(user);
		
		return "redirect:/user/userInfo?id="+user.getId();
	}
	
	@RequestMapping("/userList")
	public String userList(Model model) {
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);		
		return "userList";
		
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(
			@ModelAttribute("id") String id, Model model
			) {
		userService.removeOne(Long.parseLong(id.substring(8)));
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		
		return "redirect:/user/userList";
	}

}
