package com.example.messaging;

import com.example.dto.messaging.OrderUpdatedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderJmsProducer {

    private static final Logger LOGGER = LogManager.getLogger(OrderJmsProducer.class);
    private final GenericJmsProducer messageProducer;
    private final String orderUpdatedQueueName;

    public OrderJmsProducer(GenericJmsProducer messageProducer, String orderUpdatedQueueName) {
        this.messageProducer = messageProducer;
        this.orderUpdatedQueueName = orderUpdatedQueueName;
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
