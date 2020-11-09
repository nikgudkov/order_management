package com.example.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Value
@ApiModel(value = "OrderUpdate")
public class OrderUpdate {

    @NotNull
    @ApiModelProperty(required = true, example = "1")
    Long id;
    @NotNull @Positive
    @ApiModelProperty(required = true, example = "3.14")
    BigDecimal totalPrice;

}
