package com.ambevtech.steps;

import com.ambevtech.app.exception.EnumErrorException;
import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.core.entity.dto.CidadeTempoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import io.restassured.RestAssured;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BuscarCidadeStep {

    private io.restassured.response.Response response;
    private String nomeCidadeBusca = "";

    @Dado("^que eu desejo pesquisar uma nova pelo nome \"([^\"]*)\"$")
    public void queEuDesejoPesquisarUmaNovaPeloNome(String nome) throws Throwable {
        nomeCidadeBusca = nome;
    }

    @Quando("^enviar os dados para a API da Open Weather$")
    public void enviarOsDadosParaAAPIDaOpenWeather() throws Throwable {
        try {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("nomeCidade", nomeCidadeBusca);
            response =  RestAssured.given()
                    .port(8080)
                    .header("Content-type","application/json")
                    .params(params)
                    .get("http://localhost:8080/tempo");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(EnumErrorException.ERRO_INTERNO, e);
        }
    }

    @Então("^deve-se retornar os dados da cidade \"([^\"]*)\", \"([^\"]*)\"$")
    public void deveSeRetornarOsDadosDaCidade(String nome, Integer codigoCidade) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        List<CidadeTempoDTO> cidades = new ArrayList<>();
        cidades = Arrays.asList(mapper.readValue(response.prettyPrint(), CidadeTempoDTO[].class));
        List<CidadeTempoDTO> listaItens = cidades.stream().filter(cidade-> cidade.getNome().equals(nome) && cidade.getId().equals(codigoCidade)).collect(Collectors.toList());
        Assert.assertFalse( (listaItens.size() > 0) ? false : true);
    }

}
