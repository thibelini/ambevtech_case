package com.ambevtech.core.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AlertasTempoDTO implements Serializable {

    private String sender_name;
    private String event;
    private Long start;
    private Long end;
    private String description;

}
