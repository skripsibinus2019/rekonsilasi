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
		http.authorizeRequests().antMatchers("/","/dashboard").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		
		http.authorizeRequests().antMatchers("/","/rekonsiliasi").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/log_transaction").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/log_transaction/{id}").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/approval").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/Rekonsiliasi/List").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/approval/{id}/approve").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/approval/{id}/approve/process").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/approval/{id}/reject").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/approval/{id}/reject/process").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/approval/data").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/log_transaction/data").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/Rekonsiliasi/find").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/rekonsiliasi/{tableSourceId}/{table}").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')");
		http.authorizeRequests().antMatchers("/","/rekonsiliasi/{tableSourceId}/{table}/confirm").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')");
		http.authorizeRequests().antMatchers("/","/rekonsiliasi/{tableSourceId}/{table}/save").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')");
		http.authorizeRequests().antMatchers("/","/rekonsiliasi/status").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/rekonsiliasi/approval").access("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/rekonsiliasi/report").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		//MATCHING RULES
		http.authorizeRequests().antMatchers("/","/matching-rules/upload").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')");
		http.authorizeRequests().antMatchers("/","/matching-rules/submitMatchingUpload").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')");
		http.authorizeRequests().antMatchers("/","/matching-rules/submitMatching").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')");
		http.authorizeRequests().antMatchers("/","/matching-rules/reconciliation").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/matching-rules/List").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		
		http.authorizeRequests().antMatchers("/","/activity/list").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/activity").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		//FAVORITE
		http.authorizeRequests().antMatchers("/","/favorite/{id}").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		
		//USER ROLE
		http.authorizeRequests().antMatchers("/","/user-management/role/list").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/role").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/role/addSubmit").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/role/add").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/role/delete").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/role/edit/{id}").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/user/list").access("hasRole('ROLE_ADMIN')");
		
		//USER
		http.authorizeRequests().antMatchers("/","/user-management/user").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/user/addSubmit").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/user/delete").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/user/add").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/user/edit/{id}").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/user/editProfile").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/user-management/user/myActivity").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/user-management/user/activity/{id}").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/","/user-management/user/myActivityList").access("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR', 'ROLE_SUPERVISOR')");
		http.authorizeRequests().antMatchers("/","/user-management/user/activityList").access("hasRole('ROLE_ADMIN')");

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
