package com.ambevtech.core.entity.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CidadeListDTO implements Serializable {

    @SerializedName("list")
    private List<CidadeTempoDTO> cidades;

    public CidadeListDTO() {
        this.cidades = new ArrayList<>();
    }

}
