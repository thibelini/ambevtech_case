package com.ambevtech.core.entity.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PaisDTO implements Serializable {

    @SerializedName("country")
    private String sigla;

}
