package com.example.messaging;

import com.example.dto.messaging.OrderUpdatedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrderJmsProducer {

    private static final Logger LOGGER = LogManager.getLogger(OrderJmsProducer.class);
    private final GenericJmsProducer messageProducer;
    @Value("${messaging.queue.order-updated.name}")
    private String orderUpdatedQueueName;

    public OrderJmsProducer(GenericJmsProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void emiOrderUpdatedEvent(OrderUpdatedEvent orderUpdatedEvent) {
        try {
            messageProducer.emitQueueEvent(orderUpdatedQueueName, orderUpdatedEvent);
        } catch (Exception e) {
            //since this call is not that important for the flow we can log the exception and proceed further
            //TODO may be wrapped by circuit breaker
            LOGGER.error(e);
        }
    }

}
