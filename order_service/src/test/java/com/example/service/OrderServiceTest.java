package com.example.service;

import com.example.dto.messaging.OrderUpdatedEvent;
import com.example.dto.request.OrderCreate;
import com.example.dto.request.OrderUpdate;
import com.example.dto.response.OrderDto;
import com.example.entity.Order;
import com.example.entity.OrderStatus;
import com.example.exception.NoSuchOrderException;
import com.example.messaging.OrderJmsProducer;
import com.example.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderJmsProducer orderJmsProducer;
    @Mock
    private ModelMapper modelMapper;

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        orderService = new OrderService(orderRepository, orderJmsProducer, modelMapper);
    }

    @Test
    void shouldGetOrders() {
        //given
        Order order = new Order(1L, OrderStatus.CREATED, BigDecimal.valueOf(11.2), LocalDate.now());
        given(orderRepository.findAll()).willReturn(List.of(order));

        OrderDto orderDto = new OrderDto(1L, OrderStatus.CREATED, BigDecimal.valueOf(11.2), LocalDate.now());
        given(modelMapper.map(order, OrderDto.class)).willReturn(orderDto);

        //when
        List<OrderDto> actualOrders = orderService.getOrders();


        List<OrderDto> expectedOrders = List.of(orderDto);

        //then
        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    void shouldAddOrder() {
        //given
        OrderCreate orderCreate = new OrderCreate(BigDecimal.valueOf(11.2));

        //when
        orderService.addOrder(orderCreate);

        //then
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void shouldUpdateOrder() {
        //given
        OrderUpdate orderUpdate = new OrderUpdate(1L, BigDecimal.valueOf(10));

        Order order = new Order(1L, OrderStatus.CREATED, BigDecimal.valueOf(11.2), LocalDate.now());
        given(orderRepository.findById(orderUpdate.getId())).willReturn(Optional.of(order));
        Order changedOrder = new Order(1L, OrderStatus.CREATED, BigDecimal.valueOf(10), LocalDate.now());

        //when
        orderService.updateOrder(orderUpdate);

        //then
        verify(orderRepository, times(1)).save(changedOrder);
        verify(orderJmsProducer, times(1)).emiOrderUpdatedEvent(new OrderUpdatedEvent(order.getId(), order.getTotalPrice()));
    }


    @Test
    void shouldNotUpdateOrder() {
        //given
        OrderUpdate orderUpdate = new OrderUpdate(1L, BigDecimal.valueOf(10));
        given(orderRepository.findById(orderUpdate.getId())).willReturn(Optional.empty());

        //when
        NoSuchOrderException exception = assertThrows(NoSuchOrderException.class, () -> orderService.updateOrder(orderUpdate));

        //then
        assertEquals("Order is not present", exception.getMessage());
    }

    @Test
    void shouldRemoveOrder() {
        //given
        long id = 1;

        //when
        orderService.removeOrder(id);

        //then
        verify(orderRepository, times(1)).deleteById(id);
    }
}