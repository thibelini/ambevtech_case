package com.ambevtech.core.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class DadosTempoDTO implements Serializable {

//    @SerializedName("daily")
    private List<TempoCidadeDTO> daily;

    private List<AlertasTempoDTO> alerts;

}
