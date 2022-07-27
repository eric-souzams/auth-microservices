package com.bank.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class GatewayRoutes {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/bank/accounts/**")
                        .filters(f -> f.rewritePath("/bank/accounts/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://ACCOUNTS"))
                .route(r -> r
                        .path("/bank/loans/**")
                        .filters(f -> f.rewritePath("/bank/loans/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://LOANS"))
                .route(r -> r
                        .path("/bank/cards/**")
                        .filters(f -> f.rewritePath("/bank/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", new Date().toString()))
                        .uri("lb://CARDS"))
                .build();
    }

}
