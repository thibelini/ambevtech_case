package com.ambevtech.core.service;

import com.ambevtech.app.exception.EnumErrorException;
import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.app.util.Fn;
import com.ambevtech.core.entity.dto.CidadeListDTO;
import com.ambevtech.core.entity.dto.CidadeTempoDTO;
import com.ambevtech.core.service.api.WeatherAPI;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class WeatherAPIService {

    @Autowired
    private WeatherAPI weatherAPI;

    private List<CidadeTempoDTO> converteObjCidade(String listaCidades) {

        Gson gson = new Gson();
        CidadeListDTO listCidades = gson.fromJson(listaCidades, CidadeListDTO.class);
        List<CidadeTempoDTO> cidades = listCidades.getCidades().stream()
                .filter(Fn.distinctByKey(p -> p.getNome()) )
                .filter(Fn.distinctByKey(s -> s.getPais().getSigla()) )
                .collect( Collectors.toList() );
        return cidades;
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

}
