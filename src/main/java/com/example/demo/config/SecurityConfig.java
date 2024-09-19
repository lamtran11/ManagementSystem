package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // Define query to retrieve a user by instructor_id
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT instructor_id, password, active FROM instructors WHERE instructor_id=?"
        );

        // Define query to retrieve authorities by instructor_id
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT instructor_id, role_name FROM roles WHERE instructor_id=?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults()); // Enable CORS

        return http.build();
    }
}        