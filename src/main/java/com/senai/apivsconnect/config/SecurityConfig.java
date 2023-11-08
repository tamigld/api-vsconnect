package com.senai.apivsconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
// configurações de segurança, como permissões
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // informações de sessões pelo token (cliente)
                .authorizeHttpRequests(authorize -> authorize
                    // aqui terá cada restrição e permissão dos endpoint das requisições
                        .anyRequest().permitAll()
                        // qualquer requisição permitida
                        .requestMatchers(HttpMethod.POST, "/servicos").hasRole("CLIENTE")
                        // permite que para fazer POST no ednpoint servicos, somente quem é CLIENTE

                ).build();
                // aplicar as alterações

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        // retorna a criptografia que será utilizada na senha
        return new BCryptPasswordEncoder();
    }
}
