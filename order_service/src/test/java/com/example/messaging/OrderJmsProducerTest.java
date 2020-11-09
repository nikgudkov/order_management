package com.example.messaging;

import com.example.dto.messaging.OrderUpdatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@TestPropertySource(properties = {
        "messaging.queue.order-updated.name=order-updated"
})
class OrderJmsProducerTest {

    @Mock
    private GenericJmsProducer genericJmsProducer;

    private OrderJmsProducer orderJmsProducer;
    private String orderUpdatedQueueName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        orderUpdatedQueueName = "order-updated";
        orderJmsProducer = new OrderJmsProducer(genericJmsProducer, orderUpdatedQueueName);
    }

    @Test
    void shouldEmitQueueEvent() {
        //given
        OrderUpdatedEvent orderUpdatedEvent = new OrderUpdatedEvent(1L, BigDecimal.valueOf(11));

        //when
        orderJmsProducer.emiOrderUpdatedEvent(orderUpdatedEvent);

        //then
        verify(genericJmsProducer, times(1)).emitQueueEvent(orderUpdatedQueueName, orderUpdatedEvent);
    }


    @Test
    void shouldNotEmitQueueEvent() {
        //given
        OrderUpdatedEvent orderUpdatedEvent = new OrderUpdatedEvent(1L, BigDecimal.valueOf(11));

        //when
        doThrow(new RuntimeException()).when(genericJmsProducer).emitQueueEvent(anyString(), eq(orderUpdatedEvent));
    }
}