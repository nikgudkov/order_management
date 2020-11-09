package com.example.messaging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class GenericJmsProducerTest {

    @Mock
    private JmsTemplate jmsQueueTemplate;

    private GenericJmsProducer genericJmsProducer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        genericJmsProducer = new GenericJmsProducer(jmsQueueTemplate);
    }

    @Test
    void shouldEmitQueueEvent() {
        //given
        String destination = "test_destination";
        String message = "test_message";

        //when
        genericJmsProducer.emitQueueEvent(destination, message);

        //then
        verify(jmsQueueTemplate, times(1)).convertAndSend(destination, message);
    }
}