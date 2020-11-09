package com.example.config;

import com.example.messaging.OrderJmsProducer;
import com.example.repository.OrderRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockConfiguration {

    @Bean
    public OrderJmsProducer orderJmsProducer() {
        return Mockito.mock(OrderJmsProducer.class);
    }

    @Bean
    public OrderRepository orderRepository() {
        return Mockito.mock(OrderRepository.class);
    }

}
