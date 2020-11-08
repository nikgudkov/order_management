package com.example.messaging;

import com.example.dto.messaging.OrderUpdatedEvent;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class OrderUpdateConsumer {

    @JmsListener(destination = "${messaging.queue.order-updated.name}")
    public void processOrderUpdatedEvent(Message<OrderUpdatedEvent> orderUpdatedEventMessage) {
        //TODO store it somewhere else
        System.out.println(orderUpdatedEventMessage.getPayload());
    }

}
