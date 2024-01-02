package org.student.test.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.student.test.service.UserAdminService;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {
	
	@Autowired
	private UserAdminService userAdminService;
	
	@Bean
    public AuthenticationManager  authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
		return authenticationConfiguration.getAuthenticationManager();
    }
	
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
			return new BCryptPasswordEncoder();
	}
	
	
	@Bean 
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.csrf().disable()
		    .authorizeHttpRequests((authorize) ->
		     authorize.requestMatchers(HttpMethod.GET,"/user/**").permitAll()
		     .requestMatchers("/app/**").permitAll()
		     .requestMatchers("/useradmin/auth/**").permitAll()
		     .anyRequest().authenticated()
		     
		    		);
		return http.build();
	}

}
