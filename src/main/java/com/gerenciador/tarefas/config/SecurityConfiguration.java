package com.gerenciador.tarefas.config;

import com.gerenciador.tarefas.filter.AuthenticationFilter;
import com.gerenciador.tarefas.filter.LoginFilter;
import com.gerenciador.tarefas.permissions.PermissionEnum;
import com.gerenciador.tarefas.service.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(crsf -> crsf.disable())
                .authorizeHttpRequests(auth -> {

                    auth.requestMatchers("/login").permitAll()
                            .requestMatchers(HttpMethod.GET, "/test-api").permitAll()
                            .requestMatchers(HttpMethod.GET, "/test-api-bem-vindo").hasAuthority(PermissionEnum.ADMIN.toString())
                            .requestMatchers(HttpMethod.GET, "/users").hasAuthority(PermissionEnum.USER.toString())
                            .requestMatchers(HttpMethod.POST, "/users").hasAuthority(PermissionEnum.ADMIN.toString())
                            .anyRequest()
                            .authenticated();


                });

        http.addFilterBefore(new LoginFilter("/login", authenticationConfiguration.getAuthenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
