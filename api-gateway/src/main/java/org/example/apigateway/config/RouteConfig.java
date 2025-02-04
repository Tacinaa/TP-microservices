package org.example.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("book-service", r -> r.path("/books/**")
                        .uri("http://localhost:8085"))
                .route("user-service", r -> r.path("/users/**")
                        .uri("http://localhost:8084"))
                .route("borrowing-service", r -> r.path("/borrowings/**")
                        .uri("http://localhost:8083"))
                .build();
    }
}