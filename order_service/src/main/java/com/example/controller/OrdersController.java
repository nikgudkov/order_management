package com.example.controller;

import com.example.config.MessagingConfig;
import com.example.dto.request.OrderCreate;
import com.example.dto.request.OrderUpdate;
import com.example.dto.response.GenericResponse;
import com.example.dto.response.OrderDto;
import com.example.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(OrdersController.ORDER_CONTROLLER_ENDPOINT)
@Api(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE, tags = "CRUD operations with orders")
public class OrdersController {

    public static final String ORDER_CONTROLLER_ENDPOINT = "/order";

    private static final Logger LOGGER = LogManager.getLogger(MessagingConfig.class);

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "Get orders", notes = "Returns all existing orders",
            httpMethod = "GET", response = List.class)
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @ApiOperation(value = "Add new order", notes = "Creates new order",
            httpMethod = "PUT", response = GenericResponse.class)
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

    @ApiOperation(value = "Update existing order", notes = "Updates an order",
            httpMethod = "POST", response = GenericResponse.class)
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

    @ApiOperation(value = "Deletes existing order", notes = "Deletes an order",
            httpMethod = "DELETE", response = GenericResponse.class)
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
