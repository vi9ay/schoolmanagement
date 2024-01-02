package org.student.test.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.student.test.entity.Role;
import org.student.test.entity.UserAdmin;
import org.student.test.repository.RoleRepository;
import org.student.test.repository.UserAdminRepository;

@RestController
@RequestMapping("/useradmin/auth")
@CrossOrigin("*")
public class UserAdminController {
	
	@Autowired
	private UserAdminRepository userAdminRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> userSignup(@RequestBody UserAdmin userAdmin )
	{
		System.out.println(userAdmin);
		HashMap<String , String> response=new HashMap<>();
		if(userAdminRepository.existsByUsername(userAdmin.getUsername()))
		{
			response.put("message", "Username is already exist!!");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		if(userAdminRepository.existsByEmail(userAdmin.getEmail()))
		{
			response.put("message", "Email is already exist!!");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
		
		UserAdmin user=new UserAdmin();
		user.setName(userAdmin.getName());
		user.setEmail(userAdmin.getEmail());
		user.setUsername(userAdmin.getUsername());
		user.setPassword(passwordEncoder.encode(userAdmin.getPassword()));
		
		Optional<Role> role=roleRepository.findByName("ROLE_USER");
		
		user.setRoles(Collections.singleton(role.get()));	
		
		userAdminRepository.save(user);
		response.put("message","User Registered successfully");
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> registerUser(@RequestBody UserAdmin userAdmin)
	{
		Map<String, String> response=new HashMap<>();
		
		try
		{
			Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAdmin.getEmail(),userAdmin	.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
				
			response.put("message", "User signed-in successfully!");
		
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		catch(Exception e)
		{
			response.put("message", "Problem in Signin!");
			
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

}
