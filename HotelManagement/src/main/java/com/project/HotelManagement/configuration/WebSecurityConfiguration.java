package com.project.HotelManagement.configuration;

import com.project.HotelManagement.filter.JwtAuthenticationFilter;
import com.project.HotelManagement.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    private final UserService userDetailsService;
    private final JwtAuthenticationFilter authenticationFilter;

    public WebSecurityConfiguration(UserService userDetailsService, JwtAuthenticationFilter authenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.authenticationFilter = authenticationFilter;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { // we set the passwordEncoder to be a BCryptPasswordEncoder
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll() // Allow access to Swagger UI
                                .requestMatchers("/login", "/register").permitAll()// Allow access to login and register endpoints without prior authentication
                                .requestMatchers(HttpMethod.GET, "/api/users/{userId}", "api/reservations/user/{userId}")
                                .hasAnyAuthority("CLIENT", "ADMIN") // Allow clients to fetch their own user data
                                .requestMatchers(HttpMethod.PUT, "/api/users/{userId}", "api/reservations/user/{userId}")
                                .hasAnyAuthority("CLIENT", "ADMIN") // Allow clients to update their own user data
                                .requestMatchers(HttpMethod.POST, "api/reservations")
                                .hasAnyAuthority("CLIENT", "ADMIN") // Allow clients to make a reservation
                                .requestMatchers("/api/users/**", "api/reservations/**").hasAuthority("ADMIN") // Only allow admins to fetch all users
                                .anyRequest().authenticated()
                )
                .userDetailsService(userDetailsService)
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}