package com.bank.gatewayserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutes {

    @Autowired
    private TokenRelayGatewayFilterFactory filterFactory;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p
                        .path("/bank/accounts/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .rewritePath("/bank/accounts/(?<segment>.*)","/${segment}")
                                .removeRequestHeader("Cookie"))
                        .uri("lb://ACCOUNTS")).
                route(p -> p
                        .path("/bank/loans/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .rewritePath("/bank/loans/(?<segment>.*)","/${segment}")
                                .removeRequestHeader("Cookie"))
                        .uri("lb://LOANS")).
                route(p -> p
                        .path("/bank/cards/**")
                        .filters(f -> f.filters(filterFactory.apply())
                                .rewritePath("/bank/cards/(?<segment>.*)","/${segment}")
                                .removeRequestHeader("Cookie"))
                        .uri("lb://CARDS")).build();
    }

}
