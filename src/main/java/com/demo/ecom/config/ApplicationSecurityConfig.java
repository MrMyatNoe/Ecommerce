package com.demo.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder encoder;
	
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder encoder) {
		this.encoder = encoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		 http
         .authorizeRequests()
         .antMatchers("/api/**").hasAnyRole("ADMIN")
         .anyRequest()
         .authenticated()
         .and()
         .httpBasic();
}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails tmnUser = User.builder()
					.username("tmn")
					.password(encoder.encode("tmn"))
					.roles("ADMIN")
					.build();
		
		UserDetails driverUser = User.builder()
					.username("pp")
					.password(encoder.encode("pp"))
					.roles("DRIVER")
					.build();
		System.out.println(tmnUser + ": " + driverUser);
		
		return new InMemoryUserDetailsManager(tmnUser,driverUser);
	}

}
