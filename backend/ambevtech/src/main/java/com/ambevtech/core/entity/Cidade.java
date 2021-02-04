package com.ambevtech.core.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "cidade")
@Getter
@Setter
@ToString
public class Cidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_cidade")
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

    @Column(name = "data_criacao")
    private LocalDateTime dataCadastro = LocalDateTime.now();;


}
