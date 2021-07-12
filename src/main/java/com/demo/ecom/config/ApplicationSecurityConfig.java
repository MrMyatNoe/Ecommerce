package com.demo.ecom.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author tmn
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		 http
//		 .csrf().and()
//		 .cors().disable()
//		 //.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        // .and()
//         //.authorizeRequests()
////         .antMatchers(HttpMethod.GET,"/api/v1/categories/**")
//// 			.hasAuthority(ADMIN_READ.name())
////         .antMatchers(HttpMethod.POST,"/api/v1/categories/**").hasRole(ADMIN.name())
////         .antMatchers(HttpMethod.PUT,"/api/v1/categories/**").hasRole(ADMIN.name())
////         .antMatchers(HttpMethod.DELETE,"/api/v1/categories/**").hasRole(ADMIN.name())
////         
////		 .anonymous()
////        //.anyRequest()
////         //.permitAll()
////         //.authenticated()
////         .and()
////         .httpBasic();
//	        .authorizeRequests()  
//	            .anyRequest().authenticated()  
//	            .and()  
//	        .formLogin().and()  
//	        .httpBasic(); 
		 
		
		//http.csrf().disable();
		//http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/v1/drivers/**").permitAll();

		
		http.cors()
			.and()
			.csrf()
			.disable()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/v1/admins/login").permitAll()
			.antMatchers(HttpMethod.POST, "/v1/admins").permitAll()
			.antMatchers("/error").permitAll()
			.anyRequest().authenticated();
			//.antMatchers(HttpMethod.POST, "/admins").permitAll();
			//.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails tmnUser = User.builder()
//					.username("tmn")
//					.password(encoder.encode("tmn"))
//					//.roles(ADMIN.name())
//					.authorities(ADMIN.getGrantedAuthorities())
//					.build();
//		
//		UserDetails driverUser = User.builder()
//					.username("pp")
//					.password(encoder.encode("pp"))
//					//.roles(DRIVER.name())
//					.authorities(DRIVER.getGrantedAuthorities())
//					.build();
//		return new InMemoryUserDetailsManager(tmnUser,driverUser);
//	}

	
}
