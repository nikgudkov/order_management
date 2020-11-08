package com.example.dto.request;

import lombok.Value;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class OrderUpdate {

    @NotNull
    Long id;
    @NotNull
    BigDecimal totalPrice;

}
