package com.example.dto.response;

import com.example.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    Long id;
    OrderStatus status;
    BigDecimal totalPrice;
    LocalDate date;

}