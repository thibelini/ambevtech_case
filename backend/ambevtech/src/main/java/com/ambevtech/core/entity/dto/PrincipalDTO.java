package com.ambevtech.core.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PrincipalDTO {

    private BigDecimal temp;
    private BigDecimal feels_like;
    private BigDecimal temp_min;
    private BigDecimal temp_max;
    private BigDecimal pressure;
    private BigDecimal humidity;
    private BigDecimal sea_level;
    private BigDecimal grnd_level;

}
