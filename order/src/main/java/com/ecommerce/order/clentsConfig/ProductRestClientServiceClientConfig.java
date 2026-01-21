package com.ecommerce.order.clentsConfig;

import com.ecommerce.order.clients.ProductRestClientServiceClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ProductRestClientServiceClientConfig {

    @Bean
    @LoadBalanced
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    public ProductRestClientServiceClient restClientInterface(RestClient.Builder restClientBuilder) {
        RestClient restClient = restClientBuilder.baseUrl("http://product-service").build();
        RestClientAdapter restClientAdapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
        return factory.createClient(ProductRestClientServiceClient.class);
    }
}
