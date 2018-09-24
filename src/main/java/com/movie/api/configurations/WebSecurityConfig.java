package com.movie.api.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.movie.api.jwt.filter.JWTAuthenticationFilter;
import com.movie.api.jwt.filter.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/login";
    public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/v1/**";
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .csrf().disable() // We don't need CSRF for JWT based authentication
	        .exceptionHandling()

	        .and()
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

	        .and()
	            .authorizeRequests()
	                .antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()
	                .antMatchers("/h2-console/**/**").permitAll()
	                .antMatchers("/swagger-ui.html","/swagger-resources").permitAll()
	        .and()
	            .authorizeRequests()
	                .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated()
	        .and()
	        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			 .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	        
	        http
	            .headers()
	            .frameOptions().sameOrigin()
	            .cacheControl();
	    }
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}