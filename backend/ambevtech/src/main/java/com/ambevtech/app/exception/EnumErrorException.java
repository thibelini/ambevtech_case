package com.ambevtech.app.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum EnumErrorException {

    FALHA_COMUNICACAO_API_EXTERNA(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro",
            "Falha ao tentar acessar API externa",
            "Não foi possível acessar API externa"),

    PARAMETROS_INVALIDOS(
            HttpStatus.CONFLICT,
            "Erro",
            "Informações inválidas",
            "O corpo e/ou parametros da requisição inválidos"),

    FALHA_API(
            HttpStatus.CONFLICT,
        "Erro",
        "Falha ao tentar acessar API externa",
        "Não foi possível acessar API externa"),

    FALHA_EXTRAIR_DADOS_OBJETO(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro",
            "Falha ao extrair informações do objeto",
            "Não foi possível extrair/converter os dados do objeto no parametro"),

    FALHA_SALVAR(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro",
            "Erro ao tentar salvar a Entidade",
            "Não foi possível salvar a Entidade"),

    CIDADE_CADASTRADA(
            HttpStatus.CONFLICT,
            "Aviso",
            "Não foi possível cadastrar a Cidade",
            "A Cidade já esta Cadastrada"),

    ERRO_INTERNO(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Erro",
            "Falha ao realizar operação.",
            ""),
    ;


    EnumErrorException(HttpStatus httpStatus, String tipo, String nome, String detalhe) {
        this.httpStatus = httpStatus;
        this.tipo = tipo;
        this.nome = nome;
        this.detalhe = detalhe;
    }


    private HttpStatus httpStatus;

    private String nome;

    private String detalhe;

    private String tipo;


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getNome() {
        return nome;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public String getTipo() {
        return tipo;
    }

}