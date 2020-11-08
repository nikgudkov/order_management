package com.example.service;

import com.example.dto.messaging.OrderUpdatedEvent;
import com.example.dto.request.OrderCreate;
import com.example.dto.request.OrderUpdate;
import com.example.entity.Order;
import com.example.entity.OrderStatus;
import com.example.exception.NoSuchOrderException;
import com.example.messaging.OrderJmsProducer;
import com.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderJmsProducer orderJmsProducer;

    public OrderService(OrderRepository orderRepository, OrderJmsProducer orderJmsProducer) {
        this.orderRepository = orderRepository;
        this.orderJmsProducer = orderJmsProducer;
    }

    public List<Order> getOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Transactional
    public void addOrder(OrderCreate orderCreate) {
        Order order = Order.builder()
                .date(LocalDate.now())
                .status(OrderStatus.CREATED)
                .totalPrice(orderCreate.getTotalPrice())
                .build();
        orderRepository.save(order);
    }

    @Transactional
    public void updateOrder(OrderUpdate orderUpdate) {
        Optional<Order> orderOptional = orderRepository.findById(orderUpdate.getId());
        if (orderOptional.isEmpty()) {
            throw new NoSuchOrderException("Order is not present");
        }
        Order order = orderOptional.get();
        order.setTotalPrice(orderUpdate.getTotalPrice());
        orderRepository.save(order);
        orderJmsProducer.emiOrderUpdatedEvent(new OrderUpdatedEvent(order.getId(), order.getTotalPrice()));
    }

    @Transactional
    public void removeOrder(long id) {
        orderRepository.deleteById(id);
    }


}
