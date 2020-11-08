package com.example.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class OrderCreate {

    @NotNull
    BigDecimal totalPrice;

}
