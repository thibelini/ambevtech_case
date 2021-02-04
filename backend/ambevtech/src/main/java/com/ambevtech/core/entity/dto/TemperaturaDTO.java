package com.ambevtech.core.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TemperaturaDTO implements Serializable {

    private BigDecimal day;

    private BigDecimal min;

    private BigDecimal max;

    private BigDecimal night;

    private BigDecimal eve;

    private BigDecimal morn;

}
