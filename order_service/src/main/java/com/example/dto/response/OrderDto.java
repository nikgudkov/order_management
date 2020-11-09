package com.example.dto.response;

import com.example.entity.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrderDto {

    Long id;
    OrderStatus status;
    BigDecimal totalPrice;
    LocalDate date;

}