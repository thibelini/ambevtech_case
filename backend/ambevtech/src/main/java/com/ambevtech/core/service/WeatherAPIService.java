package com.ambevtech.core.service;

import com.ambevtech.app.exception.EnumErrorException;
import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.app.util.Fn;
import com.ambevtech.core.entity.dto.CidadeListDTO;
import com.ambevtech.core.entity.dto.CidadeTempoDTO;
import com.ambevtech.core.entity.dto.DadosTempoDTO;
import com.ambevtech.core.service.api.WeatherAPI;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherAPIService {

    @Autowired
    private WeatherAPI weatherAPI;

    public Object buscarDadosTempoCidade(BigDecimal latitude, BigDecimal longitude) {
        var response = weatherAPI.buscarDadosTempoCidade(latitude, longitude);
        switch (response.getStatusCodeValue()) {
            case 200: {
                return converteObjTempo(response.getBody());
            }
            default: {
                throw new ServiceException(EnumErrorException.FALHA_API);
            }
        }
    }

    public List<CidadeTempoDTO> buscarCidade(String nomeCidade) {
        var response = weatherAPI.buscarCidade(nomeCidade);
        switch (response.getStatusCodeValue()) {
            case 200: {
                return converteObjCidade(response.getBody());
            }
            default: {
                throw new ServiceException(EnumErrorException.FALHA_API);
            }
        }
    }

    private List<CidadeTempoDTO> converteObjCidade(String listaCidades) {
        Gson gson = new Gson();
        CidadeListDTO listCidades = gson.fromJson(listaCidades, CidadeListDTO.class);
        List<CidadeTempoDTO> cidades = listCidades.getCidades().stream()
                .filter(Fn.distinctByKey(p -> p.getNome()) )
                .filter(Fn.distinctByKey(s -> s.getPais().getSigla()) )
                .collect( Collectors.toList() );
        return cidades;
    }

    private DadosTempoDTO converteObjTempo(String dados){
        Gson gsons = new Gson();
        DadosTempoDTO tempoCidade = gsons.fromJson(dados, DadosTempoDTO.class);
        tempoCidade.getDaily().stream().limit(5).collect(Collectors.toList());
        return tempoCidade;
    }

}
