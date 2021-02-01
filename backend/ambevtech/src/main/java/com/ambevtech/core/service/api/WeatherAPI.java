package com.ambevtech.core.service.api;

import com.ambevtech.core.entity.dto.CidadeListDTO;
import com.ambevtech.core.entity.dto.CidadeTempoDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.List;

@Lazy
@Service
public class WeatherAPI {

    @Value("${api-externa.openweather.url}")
    String url;

    @Value("${api-externa.openweather.token}")
    String token;

    @Value("${api-externa.openweather.language}")
    String language;

    @Value("${api-externa.openweather.units}")
    String units;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> buscarCidade(String nomeCidade){
        String path = "/data/2.5/find?";
        try {
            StringBuilder uri = new StringBuilder();
            uri
                    .append(url)
                    .append(path)
                    .append("q="+ nomeCidade)
                    .append("&appid="+ token)
                    .append("&lang="+ language)
                    .append("&units="+ units);
            ResponseEntity<String> response = restTemplate.getForEntity(uri.toString(), String.class);
            return response;
        } catch (HttpStatusCodeException ex) {
            return new ResponseEntity<String>(ex.getResponseBodyAsString(), ex.getResponseHeaders(), ex.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> buscarDadosTempoCidade(BigDecimal latitude, BigDecimal longitude){

        String path = "/data/2.5/onecall?";
        try {
            StringBuilder uri = new StringBuilder();
            uri
                    .append(url)
                    .append(path)
                    .append("lat="+ latitude)
                    .append("&lon="+ longitude)
                    .append("&appid="+ token)
                    .append("&lang="+ language)
                    .append("&units="+ units);
            ResponseEntity<String> response = restTemplate.getForEntity(uri.toString(), String.class);
            return response;
        } catch (HttpStatusCodeException ex) {
            return new ResponseEntity<String>(ex.getResponseBodyAsString(), ex.getResponseHeaders(), ex.getStatusCode());
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
