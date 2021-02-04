package com.ambevtech.core.service;

import com.ambevtech.app.config.CacheNames;
import com.ambevtech.app.exception.EnumErrorException;
import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.core.entity.dto.CidadeListDTO;
import com.ambevtech.core.entity.dto.CidadeTempoDTO;
import com.ambevtech.core.entity.dto.DadosTempoDTO;
import com.ambevtech.core.entity.dto.TempoCidadeDTO;
import com.ambevtech.core.service.api.WeatherAPI;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherAPIService {

    private final Logger logger = LoggerFactory.getLogger(WeatherAPIService.class);

    @Autowired
    private WeatherAPI weatherAPI;

    /**
     * @author Thiago Belini
     * @param 'BigDecimal' - Parametros usados para filtrar a cidade na API da Open Weather
     * @return 'Object' - Retorno do objeto contendo a cidade
     * @throws 'ServiceException'
     * @method Método responsável por buscar os dados do tempo de uma cidade na API da Open Weather
     */
    @Cacheable(value = CacheNames.cacheDadosPrevisao, key = "{#latitude, #longitude}")
    public Object buscarDadosTempoCidade(BigDecimal latitude, BigDecimal longitude) throws ServiceException {
        logger.info("Consultando dados do tempo na API da OpenWeatherApi para a latitude e longitude: " + latitude +", "+ longitude);
        ResponseEntity<String> response = weatherAPI.buscarDadosTempoCidade(latitude, longitude);
        switch (response.getStatusCodeValue()) {
            case 200: {
                return converteObjTempo(response.getBody());
            }
            default: {
                throw new ServiceException(EnumErrorException.FALHA_API);
            }
        }
    }

    /**
     * @author Thiago Belini
     * @param 'String' - Parametro usados para filtrar por nome na API da Open Weather
     * @return 'List<CidadeTempoDTO>' - Retorno de uma lista de cidade da API da Open Weather
     * @throws 'ServiceException'
     * @method Método responsável por buscar por nome uma determinada cidade na API da Open Weather
     */
    public List<CidadeTempoDTO> buscarCidade(String nomeCidade) throws ServiceException {
        logger.info("Consultando dados da cidade na API da OpenWeatherApi: "+ nomeCidade);
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

    /**
     * @author Thiago Belini
     * @param 'String' - Parametro contendo a lista das cidades não formatadas vindo da API da Open Weather
     * @return 'List<CidadeTempoDTO>' - Retorno de uma lista de cidade da API da Open Weather
     * @throws 'ServiceException'
     * @method Método responsável por converter o objeto de retorno da API da Open Weather em um DTO.
     */
    private List<CidadeTempoDTO> converteObjCidade(String listaCidades) throws ServiceException {
        Gson gson = new Gson();
        CidadeListDTO listCidades = gson.fromJson(listaCidades, CidadeListDTO.class);
        List<CidadeTempoDTO> cidades = listCidades.getCidades();
        logger.info("Retornando os dados das cidades encontradas na API da OpenWeatherApi: " + cidades);
        return cidades;
    }

    /**
     * @author Thiago Belini
     * @param 'String' - Parametro contendo a lista das dados do tempo de uma determinada cidade vindo da API da Open Weather
     * @return 'DadosTempoDTO' - Retorno de objeto com os dados do tempo
     * @throws 'ServiceException'
     * @method Método responsável por converter o objeto de retorno da API da Open Weather em um DTO com os dados do tempo de
     * uma determinada cidade
     */
    private DadosTempoDTO converteObjTempo(String dados) throws ServiceException {
        Gson gsons = new Gson();
        DadosTempoDTO tempoCidade = gsons.fromJson(dados, DadosTempoDTO.class);
        List<TempoCidadeDTO> cidades = tempoCidade.getDaily().stream().limit(5).collect(Collectors.toList());
        tempoCidade.setDaily(cidades);
        logger.info("Retornando os dados do tempo da API da OpenWeatherApi");
        return tempoCidade;
    }

}
