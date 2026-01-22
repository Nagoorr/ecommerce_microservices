package com.ecommerce.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator customRouter(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes().route("user-service", r ->
                        r.path("/users/**").filters(f-> f.rewritePath("/users(?<segment>/?.*)","/v1/users${segment}"))
                                .uri("lb://USER-SERVICE"))
                .route("product-service", r ->
                        r.path("/v1/products/**").uri("lb://PRODUCT-SERVICE"))
                .route("order-service", r ->
                        r.path("/v1/orders/**", "/v1/cart/**").uri("lb://ORDER-SERVICE"))
                .route("eureka-server", r ->
                        r.path("/eureka/main")
                                .filters(f -> f.rewritePath("/eureka/main", "/")).uri("http://localhost:8761"))
                .route("eureka-server-static", r ->
                        r.path("/eureka/**").uri("http://localhost:8761"))
                .build();
    }
}
