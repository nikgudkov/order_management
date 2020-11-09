package com.example.config;

import com.example.messaging.GenericJmsProducer;
import com.example.messaging.OrderJmsProducer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    private static final Logger LOGGER = LogManager.getLogger(MessagingConfig.class);

    @Value("${spring.activemq.broker-url}")
    private String brokerUrl;
    @Value("${spring.activemq.user}")
    private String user;
    @Value("${spring.activemq.password}")
    private String password;

    @Value("${messaging.queue.order-updated.name}")
    private String orderUpdatedQueueName;


    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES", "*");
        return new ActiveMQConnectionFactory(user, password, brokerUrl);
    }

    @Bean
    public OrderJmsProducer orderJmsProducer(GenericJmsProducer genericJmsProducer) {
        return new OrderJmsProducer(genericJmsProducer, orderUpdatedQueueName);
    }

}
