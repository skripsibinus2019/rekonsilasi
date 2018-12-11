package com.rekonsiliasi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.rekonsiliasi.service.LoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	LoginService loginServiceImpl;
	
	@Bean
	  public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	  };
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(loginServiceImpl);
		return authenticationProvider;
	}
	
	
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/","/dashboard").access("hasRole('ROLE_ADMIN')");
		
		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/j_spring_security_check")
			.loginPage("/login")
			.defaultSuccessUrl("/dashboard")
			.failureUrl("/login?error=true")
			.usernameParameter("username")
			.passwordParameter("password")
			.and().logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login");
		
		
	}
	
}
