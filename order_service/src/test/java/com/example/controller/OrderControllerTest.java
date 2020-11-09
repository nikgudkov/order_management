package com.example.controller;

import com.example.dto.request.OrderCreate;
import com.example.dto.request.OrderUpdate;
import com.example.dto.response.GenericResponse;
import com.example.dto.response.OrderDto;
import com.example.entity.OrderStatus;
import com.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        orderController = new OrderController(orderService);
    }

    @Test
    void shouldGetOrders() {
        //given
        OrderDto orderDto = new OrderDto(1L, OrderStatus.CREATED, BigDecimal.valueOf(11.2), LocalDate.now());
        List<OrderDto> expectedOrders = List.of(orderDto);
        given(orderService.getOrders()).willReturn(List.of(orderDto));

        //when
        List<OrderDto> actualOrders = orderController.getOrders();

        //then
        assertEquals(expectedOrders, actualOrders);
    }

    @Test
    void shouldAddOrder() {
        //given
        OrderCreate orderCreate = new OrderCreate(BigDecimal.valueOf(11.2));

        //when
        ResponseEntity<GenericResponse> genericResponseResponseEntity = orderController.addOrder(orderCreate);

        //then
        assertEquals(HttpStatus.OK, genericResponseResponseEntity.getStatusCode());
        assertEquals("Order has been created", genericResponseResponseEntity.getBody().getMessage());
    }

    @Test
    void shouldNotAddOrder() {
        //given
        OrderCreate orderCreate = new OrderCreate(BigDecimal.valueOf(11.2));
        doThrow(new RuntimeException()).when(orderService).addOrder(orderCreate);
        //when

        ResponseEntity<GenericResponse> genericResponseResponseEntity = orderController.addOrder(orderCreate);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, genericResponseResponseEntity.getStatusCode());
        assertEquals("Order wasn't created", genericResponseResponseEntity.getBody().getMessage());
    }

    @Test
    void shouldUpdateOrder() {
        //given
        OrderUpdate orderUpdate = new OrderUpdate(1L, BigDecimal.valueOf(10));
        //when
        ResponseEntity<GenericResponse> genericResponseResponseEntity = orderController.updateOrder(orderUpdate);

        //then
        assertEquals(HttpStatus.OK, genericResponseResponseEntity.getStatusCode());
        assertEquals("Order has been updated", genericResponseResponseEntity.getBody().getMessage());
    }

    @Test
    void shouldNotUpdateOrder() {
        //given
        OrderUpdate orderUpdate = new OrderUpdate(1L, BigDecimal.valueOf(10));
        doThrow(new RuntimeException()).when(orderService).updateOrder(orderUpdate);

        //when
        ResponseEntity<GenericResponse> genericResponseResponseEntity = orderController.updateOrder(orderUpdate);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, genericResponseResponseEntity.getStatusCode());
        assertEquals("Order wasn't updated", genericResponseResponseEntity.getBody().getMessage());
    }

    @Test
    void shouldRemoveOrder() {
        //given
        long id = 1;

        //when
        ResponseEntity<GenericResponse> genericResponseResponseEntity = orderController.removeOrder(id);

        //then
        assertEquals(HttpStatus.OK, genericResponseResponseEntity.getStatusCode());
        assertEquals("Order has been deleted", genericResponseResponseEntity.getBody().getMessage());
    }

    @Test
    void shouldNotRemoveOrder() {
        //given
        long id = 1;
        doThrow(new RuntimeException()).when(orderService).removeOrder(id);

        //when
        ResponseEntity<GenericResponse> genericResponseResponseEntity = orderController.removeOrder(id);

        //then
        assertEquals(HttpStatus.BAD_REQUEST, genericResponseResponseEntity.getStatusCode());
        assertEquals("Order wasn't deleted", genericResponseResponseEntity.getBody().getMessage());
    }
}