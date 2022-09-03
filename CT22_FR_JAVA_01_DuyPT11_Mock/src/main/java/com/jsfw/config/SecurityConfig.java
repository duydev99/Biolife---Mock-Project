package com.jsfw.config;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jsfw.models.Tbl_User;
import com.jsfw.repositories.UserRepository;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserRepository userReponsitory;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Tbl_User user = userReponsitory.findByUsername(username);
				String password = user.getPassword() ;
				//String password = passwordEncoder().encode(user.getPassword()) ;
				Integer role = user.getType();
				return User.withUsername(username).password(password).roles(role.toString()).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + " not fount!");
			}
		});
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//chặn request giả lập
		http.csrf().disable();
		//http.authorizeRequests().antMatchers("/order/**").authenticated()
				// .antMatchers("/admin/**","/assets/admin/**","/rest/categories/**").hasAnyRole("STAF","DIRE")
				//.anyRequest().permitAll();
		
		http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
				.defaultSuccessUrl("/security/login/success", true).failureUrl("/security/login/error");

		http.rememberMe().tokenValiditySeconds(86400);

		http.exceptionHandling().accessDeniedPage("/security/unauthoried");

		http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");

//		http.oauth2Login().loginPage("/security/login/form").defaultSuccessUrl("/security_oauth2/login/success", true)
//				.failureUrl("/auth/login/error").authorizationEndpoint().baseUri("/oauth2/authorization");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
