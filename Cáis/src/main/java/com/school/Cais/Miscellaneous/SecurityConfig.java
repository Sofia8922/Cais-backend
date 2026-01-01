package com.school.Cais.Miscellaneous;

import com.school.Cais.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
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
    @Autowired
    private CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/accounts/login", "/accounts/register", "/accounts/logout").permitAll()
                        .requestMatchers(HttpMethod.GET,"/accounts").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/accounts/*").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/accounts/*/cart/*", "/accounts/*/cart", "/accounts/*/favorites/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/accounts/*/cart/*", "/accounts/*/favorites/*").authenticated()

                        .requestMatchers(HttpMethod.POST, "/accounts/*").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/accounts/*").authenticated()
                        .requestMatchers(HttpMethod.POST, "/accounts/*/checkout").authenticated()

                        .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/categories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/categories/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/subcategories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/subcategories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/subcategories/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/subcategories/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                        .anyRequest().authenticated()
//                                .anyRequest().permitAll()
                )

//                .httpBasic(AbstractHttpConfigurer::disable)
                .userDetailsService(userDetailsService)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
