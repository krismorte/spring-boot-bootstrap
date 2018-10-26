package com.krismorte.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.krismorte.secutiry.CustomAuthenticationFailureHandler;
import com.krismorte.secutiry.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	@Autowired
    private CustomUserDetailsService userDatailService;
	
	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
		return new LoginUrlHandler();
	}
	
	@Bean
	public SimpleUrlAuthenticationFailureHandler myAuthenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// permissões em diretorios
				.antMatchers("/").permitAll().antMatchers("/bower_components/**").permitAll()
				.antMatchers("/bootstrap/**").permitAll().antMatchers("/dist/**").permitAll().antMatchers("/css/**")
				.permitAll().antMatchers("/plugins/**").permitAll().antMatchers("/util/**").permitAll()
				.antMatchers("/assets/**").permitAll().antMatchers("/img/**").permitAll().antMatchers("/images/**")
				.permitAll().antMatchers("/register").permitAll()

				.and().logout().logoutUrl("/logout").invalidateHttpSession(true).and().formLogin()
				.loginPage("/login").successHandler(myAuthenticationSuccessHandler())
				.failureHandler(myAuthenticationFailureHandler())
				.permitAll().and().authorizeRequests().anyRequest().authenticated().and().httpBasic();

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDatailService);
		/*auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER")
				.and().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");*/
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
