package com.depaseo;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.depaseo.auth.JWTAuthenticationFilter;
import com.depaseo.auth.JWTAuthorizationFilter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecuriyConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired DataSource datasource;
	
	@Autowired
	public void configurerGlobal (AuthenticationManagerBuilder builder) throws Exception {
		//PasswordEncoder encoder = passwordEncoder();
		//UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		/*
		try {
			builder.inMemoryAuthentication().withUser(users.username("user").password("admin1234").roles("ADMIN"));
		} catch (Exception e) {
			
			log.info(e.getMessage());
		}
		*/
		try {
		builder.jdbcAuthentication()
		.dataSource(datasource)
		.passwordEncoder(passwordEncoder())
		.usersByUsernameQuery("select username, password, enabled from partner where username=?")
		.authoritiesByUsernameQuery("select username, authority from partner where username=?");
		} catch (Exception e) {
			
			log.info(e.getMessage());
		}
		
	}
	


	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.cors()
		.and().authorizeRequests().antMatchers("/index","/css/**","/","/js/**","/images/**","/reservasTicket","/horario/**","/reservasTicketOption","/api/calendar","/api/autobus","/api/reservar","/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger.json","/csrf","/api/enviarmail").permitAll()
		.anyRequest().authenticated()
	//	.and()
	//	.requiresChannel().anyRequest().requiresSecure()  //para el ssl
		.and()
		.addFilter(new JWTAuthenticationFilter(authenticationManager()))
		.addFilter(new JWTAuthorizationFilter(authenticationManager()))
		.csrf().disable()
		
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public CorsFilter corsFilter() {

	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    //config.setAllowCredentials(true); // you USUALLY want this
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("OPTIONS");
	    config.addAllowedMethod("HEAD");
	    config.addAllowedMethod("GET");
	    config.addAllowedMethod("PUT");
	    config.addAllowedMethod("POST");
	    config.addAllowedMethod("DELETE");
	    config.addAllowedMethod("PATCH");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
}
