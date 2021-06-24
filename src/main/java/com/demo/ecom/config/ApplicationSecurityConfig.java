//package com.demo.ecom.config;
//
//import static com.demo.ecom.config.ApplicationUserPermission.ADMIN_READ;
//import static com.demo.ecom.config.ApplicationUserRole.ADMIN;
//import static com.demo.ecom.config.ApplicationUserRole.DRIVER;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) 
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
//
//	private final PasswordEncoder encoder;
//	
//	@Autowired
//	public ApplicationSecurityConfig(PasswordEncoder encoder) {
//		this.encoder = encoder;
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
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
//}
//
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
//
//}
