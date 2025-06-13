
package com.example.traffic_signal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails userDetails1 = User.withUsername("User1").password(passwordEncoder().encode("Pass1")).build();
		UserDetails userDetails2 = User.withUsername("User2").password(passwordEncoder().encode("Pass2")).build();
		UserDetails admin = User.withUsername("Admin1").password(passwordEncoder().encode("Pass3")).build();
		
		return new InMemoryUserDetailsManager(userDetails1, userDetails2, admin);

	}

	@Bean
	 PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/signals/**").authenticated().anyRequest().permitAll())
				.httpBasic(Customizer.withDefaults());
		//http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();

	}

}
