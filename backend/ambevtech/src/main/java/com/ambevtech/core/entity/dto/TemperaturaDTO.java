package com.ambevtech.core.entity.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TemperaturaDTO implements Serializable {

//    @SerializedName("day")
    private BigDecimal day;

    private BigDecimal min;

    private BigDecimal max;

//    @SerializedName("night")
    private BigDecimal night;

//    @SerializedName("eve")
    private BigDecimal eve;

//    @SerializedName("eve")
    private BigDecimal morn;

}
