package com.ambevtech.steps;

import com.ambevtech.SpringIntegrationTest;
import com.ambevtech.app.exception.EnumErrorException;
import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.core.entity.Cidade;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import io.restassured.RestAssured;
import org.junit.Assert;

import java.math.BigDecimal;

public class CadastrarCidadeStep extends SpringIntegrationTest {

    private io.restassured.response.Response response;
    private Cidade cidade;

    @Dado("^que eu desejo cadastrar uma nova cidade \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void queEuDesejoCadastrarUmaNovaCidade(String codigoCidade, String nome, String lat, String lon, String pais) throws Throwable {
        cidade = new Cidade();
        cidade.setNome(nome);
        cidade.setCodigoCidade(Integer.parseInt(codigoCidade));
        cidade.setLatitude(new BigDecimal(lat));
        cidade.setLongitude(new BigDecimal(lon));
        cidade.setPais(pais);
    }

    @Quando("^enviar os dados para a API de cadastro$")
    public void enviarOsDadosParaAAPIDeCadastro() throws Throwable {
        try {
            response =  RestAssured.given()
                    .port(8080)
                    .header("Content-type","application/json")
                    .body(cidade)
                    .post("http://localhost:8080/cidades");

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(EnumErrorException.ERRO_INTERNO, e);
        }
    }

    @Então("^deve-se criar uma nova cidade e retornar os dados \"([^\"]*)\", \"([^\"]*)\"$")
    public void deveSeCriarUmaNovaCidadeERetornarOsDados(String statusCode, String mensagem) throws Throwable {
        int statusCodeInt = Integer.parseInt(statusCode);
        int statusCodeIntReq = response.statusCode();
        Assert.assertFalse(statusCodeIntReq != statusCodeInt);
    }


}
