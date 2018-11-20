package com.example.nextnote.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
				.csrf().disable() // Disable CSRF
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Create stateless sessions
				.and()
				.authorizeRequests()
				.anyRequest().permitAll(); // Allow everything
	}
}