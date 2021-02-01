package com.ambevtech.core.entity.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

@Getter
@Setter
public class FiltroDTO<T> implements Serializable {

    private T obj;

    private int page = 0;

    private int size = 5;

    private Sort sort;

}