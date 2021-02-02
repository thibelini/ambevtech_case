package com.ambevtech.core.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TempoCidadeDTO implements Serializable {

    private Long dt;

    private TemperaturaDTO temp;

    private Integer pressure;

    private Integer humidity;

    private String rain;

    private List<ClimaDTO> weather;

}
