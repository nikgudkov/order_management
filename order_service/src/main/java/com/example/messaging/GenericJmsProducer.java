package com.example.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class GenericJmsProducer {

    private final JmsTemplate jmsQueueTemplate;

    public GenericJmsProducer(JmsTemplate jmsQueueTemplate) {
        this.jmsQueueTemplate = jmsQueueTemplate;
    }

    public void emitQueueEvent(String destination, Object message) {
        jmsQueueTemplate.convertAndSend(destination, message);
    }

}
