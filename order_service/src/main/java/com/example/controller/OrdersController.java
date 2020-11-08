package com.example.controller;

import com.example.config.MessagingConfig;
import com.example.dto.request.OrderCreate;
import com.example.dto.request.OrderUpdate;
import com.example.dto.response.GenericResponse;
import com.example.entity.Order;
import com.example.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrdersController {

    private static final Logger LOGGER = LogManager.getLogger(MessagingConfig.class);

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GenericResponse> addOrder(@Valid @RequestBody OrderCreate orderCreate) {
        try {
            orderService.addOrder(orderCreate);
        } catch (Exception e) {
            LOGGER.error(e);
            return ResponseEntity.badRequest()
                    .body(new GenericResponse("Order wasn't created"));
        }
        return ResponseEntity.ok().body(new GenericResponse("Order has been created"));
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GenericResponse> updateOrder(@Valid @RequestBody OrderUpdate orderUpdate) {
        try {
            orderService.updateOrder(orderUpdate);
        } catch (Exception e) {
            LOGGER.error(e);
            return ResponseEntity.badRequest()
                    .body(new GenericResponse("Order wasn't updated"));
        }
        return ResponseEntity.ok().body(new GenericResponse("Order has been updated"));
    }

    @DeleteMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<GenericResponse> removeOrder(@RequestParam("id") long id) {
        try {
            orderService.removeOrder(id);
        } catch (Exception e) {
            LOGGER.error(e);
            return ResponseEntity.badRequest()
                    .body(new GenericResponse("Order wasn't deleted"));
        }
        return ResponseEntity.ok().body(new GenericResponse("Order has been deleted"));
    }


}
