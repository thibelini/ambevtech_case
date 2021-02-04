package com.ambevtech.core.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class CidadeDTO {

    @NotNull(message= "Codigo da Cidade não pode estar vazio")
    @Range(min = 1)
    private Integer codigoCidade;

    @NotNull
    @NotBlank
    private String nome;

    @NotNull
    @NotBlank
    private String pais;

    @Digits(integer = 5, fraction = 4, message = "Latitude no formato incorreto.")
    private BigDecimal latitude;

    @Digits(integer = 5, fraction = 4, message = "Longitude no formato incorreto.")
    private BigDecimal longitude;

}
