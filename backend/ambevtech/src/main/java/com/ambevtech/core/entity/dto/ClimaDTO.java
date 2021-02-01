package com.ambevtech.core.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ClimaDTO implements Serializable {

    private String description;

    private String icon;

}
