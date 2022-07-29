package com.bank.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/bank/accounts/**").authenticated()
                        .pathMatchers("/bank/cards/**").authenticated()
                        .pathMatchers("/bank/loans/**").authenticated())
                .oauth2Login(Customizer.withDefaults());
        http.csrf().disable();
        return http.build();
    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        http.authorizeExchange(exchenge -> exchenge
//                .pathMatchers("/bank/accounts/**").authenticated()
//                .pathMatchers("/bank/loans/**").authenticated()
//                .pathMatchers("/bank/cards/**").authenticated())
//                .oauth2Login(Customizer.withDefaults());
////            .oauth2ResourceServer().jwt()
//        http.csrf().disable();
//
//        return http.build();
//    }

//    public Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
//
//        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
//    }

}
