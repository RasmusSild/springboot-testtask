package com.srini.testtask.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
	  throws Exception {
	    auth.jdbcAuthentication()
	      .passwordEncoder(new BCryptPasswordEncoder())
	      .dataSource(dataSource)
	      .usersByUsernameQuery("select username,password,enabled "
	        + "from users "
	        + "where username = ?")
	      .authoritiesByUsernameQuery("select username,authority "
	        + "from authorities "
	        + "where username = ?");
	}

	@Override
    protected void configure(HttpSecurity httpSecurity)
      throws Exception {
        httpSecurity.authorizeRequests()
          .antMatchers("/h2-console/**")
          .permitAll()
          .anyRequest()
          .authenticated()
          .and()
          .formLogin()
          .and()
          .logout();
         
        httpSecurity.csrf()
          .ignoringAntMatchers("/h2-console/**");
        httpSecurity.headers()
          .frameOptions()
          .sameOrigin();
    }

	
}
