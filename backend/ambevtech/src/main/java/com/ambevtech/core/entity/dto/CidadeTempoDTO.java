package com.ambevtech.core.entity.dto;

import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
