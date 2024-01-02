package org.student.test.service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.student.test.entity.Role;
import org.student.test.entity.UserAdmin;
import org.student.test.repository.UserAdminRepository;

@Service
public class UserAdminService implements UserDetailsService {
	
	@Autowired
	private UserAdminRepository userAdminRepository;

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
try {
			
			UserAdmin user=userAdminRepository.findByEmailOrUsername( usernameOrEmail, usernameOrEmail)
					.orElseThrow(() ->new Exception("Username or email not found!!"));
			
		return new User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
		
		} catch (Exception e) {
			
		}

		return null;
	}
	
	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles)
	{
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());	
	}

}
