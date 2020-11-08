package com.example.dto.messaging;

import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

@Value
public class OrderUpdatedEvent implements Serializable {

    Long id;
    BigDecimal totalPrice;

}
