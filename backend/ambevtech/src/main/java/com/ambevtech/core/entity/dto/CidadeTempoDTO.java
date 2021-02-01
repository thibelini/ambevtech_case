package com.ambevtech.core.entity.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CidadeTempoDTO implements Serializable {

    private Integer id;

    @SerializedName("name")
    private String nome;

    @SerializedName("coord")
    private CoordenadasDTO coordenadas;

    private Integer dt;

    @SerializedName("sys")
    private PaisDTO pais;

}
