package com.ecommerce.order.clients;

import com.ecommerce.order.dto.UserDTO;
import com.ecommerce.order.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserWebClientService {
    private final WebClient webClient;

    public UserWebClientService(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Mono<UserResponse> findById(String userId) {
        return webClient.get()
                .uri("http://USER-SERVICE/v1/getUserById/{id}", userId)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }
}
