package com.ambevtech.core.controller;

import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.core.service.WeatherAPIService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Api(value = "Api Tempo")
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "tempo")
public class WeatherAPIResource {

    @Autowired
    private WeatherAPIService weatherAPIService;

    @ApiOperation(value = "Realiza a busca das cidades na Api da OpenWeather para o cadastro no Sistema", notes = "Retorna as cidades da API")
    @GetMapping
    public ResponseEntity<?> buscarCidade(@ApiParam(required = true, value = "nome da cidade", type = "String")
                                              @RequestParam String nomeCidade) throws ServiceException {
        return ResponseEntity.ok(weatherAPIService.buscarCidade(nomeCidade));
    }

    @ApiOperation(value = "Realiza a busca dos dados do tempo de acordo com a Latitude e Longitude", notes = "Retorna os dados do tempo da cidade selecionada")
    @GetMapping("/dados")
    public ResponseEntity<?> buscarDadosTempoCidade(@ApiParam(required = true, value = "latidude da cidade", type = "BigDecimal")
                                                        @RequestParam BigDecimal latitude,
                                                    @ApiParam(required = true, value = "longitude da cidade", type = "BigDecimal")
                                                        @RequestParam BigDecimal longitude) throws ServiceException {
        return ResponseEntity.ok(weatherAPIService.buscarDadosTempoCidade(latitude, longitude));
    }


}
