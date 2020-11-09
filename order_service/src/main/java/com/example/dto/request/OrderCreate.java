package com.example.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "OrderCreate")
public class OrderCreate {

    @NotNull @Positive
    @ApiModelProperty(required = true, example = "3.50")
    BigDecimal totalPrice;

}
