package com.ambevtech.core.service;

import com.ambevtech.app.exception.EnumErrorException;
import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.app.util.Fn;
import com.ambevtech.core.entity.Cidade;
import com.ambevtech.core.entity.dto.CidadeDTO;
import com.ambevtech.core.entity.dto.FiltroDTO;
import com.ambevtech.core.repository.CidadeRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private ModelMapper modelMapper;

    private final Logger logger = LoggerFactory.getLogger(CidadeService.class);

    /**
     * @author Thiago Belini
     * @param 'FiltroDTO<Cidade>' - Parametro usado para filtrar a busca das cidades
     * @return 'Page<Cidade>' - Retorno das cidades filtras e paginadas
     * @throws 'ServiceException'
     * @method Método responsável por listar as cidades cadastradas no banco de dados
     */
    public Page<Cidade> filtrar(FiltroDTO<Cidade> filtro) throws ServiceException {
        Page<Cidade> list = null;
        try {
            Pageable pageable = PageRequest.of(filtro.getPage(), filtro.getSize());
            list = cidadeRepository.findAllOrderByNome(filtro.getObj().getNome(), pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list.map(cidades -> {
            return modelMapper.map(cidades, Cidade.class);
        });

    }

    /**
     * @author Thiago Belini
     * @param 'CidadeDTO' - Parametro contendo os dados da cidade
     * @throws 'ServiceException'
     * @method Método principal responsável por chamar o metodo de validação e  de salvar a cidade no banco de dados
     */
    public void salvarCidade(CidadeDTO dto) throws ServiceException {
        logger.info("Início do cadastro da cidade: " + dto);
        validar(dto);
        salvar(dto);
        logger.info("Cidade cadastrada com sucesso " + dto);
    }

    /**
     * @author Thiago Belini
     * @param 'CidadeDTO' - Parametro contendo os dados da cidade
     * @throws 'ServiceException'
     * @method Método responsável por validar a venda antes de salvar no banco de dados
     */
    private void validar(CidadeDTO dto) throws ServiceException {
        logger.info("Validando a cidade: " + dto);
        if (dto == null) {
           throw new ServiceException(EnumErrorException.PARAMETROS_INVALIDOS);
        }

        if (Fn.isEmpty(dto.getLatitude()) || Fn.isEmpty(dto.getLongitude())) {
           throw new ServiceException(EnumErrorException.PARAMETROS_INVALIDOS, "A Latitude e Longitude são campos obrigatórios");
        }

        Cidade cidadeValida = buscarPorCodigoCidade(dto.getCodigoCidade());
        if (cidadeValida != null) {
           logger.info("Cidade já cadastrada no sistema: " + cidadeValida);
           throw new ServiceException(EnumErrorException.CIDADE_CADASTRADA);
        }
    }

    /**
     * @author Thiago Belini
     * @param 'CidadeDTO' - Parametro usado para cadastrar a cidades
     * @throws 'ServiceException'
     * @method Método responsável por salvar fisicamente a cidade no banco de dados
     */
    private void salvar(CidadeDTO dto) throws ServiceException {
        logger.info("Salvando a cidade: " + dto);
        Cidade cidade = new Cidade();
        modelMapper.map(dto, cidade);
        try {
            cidade = cidadeRepository.save(cidade);
        } catch (Exception e) {
            throw new ServiceException(EnumErrorException.FALHA_SALVAR, "Erro ao Salvar a Cidade");
        }
        modelMapper.map(cidade, CidadeDTO.class);
    }

    /**
     * @author Thiago Belini
     * @param 'Integer' - Parametro usado para filtrar a cidade
     * @return 'Cidade' - Retorno das cidades filtras
     * @throws 'ServiceException'
     * @method Método responsável por buscar uma cidade, para posteriormente validar se a mesma já foi salva no banco de dados
     */
    private Cidade buscarPorCodigoCidade(Integer codigoCidade) throws ServiceException {
        if (Fn.isEmpty(codigoCidade)) {
            throw new ServiceException(EnumErrorException.PARAMETROS_INVALIDOS);
        }
        Cidade cidade = null;
        try {
            cidade = cidadeRepository.findByCodigoCidade(codigoCidade);
        } catch (Exception e) {
            throw new ServiceException(EnumErrorException.FALHA_SALVAR, "Erro ao Salvar a Cidade");
        }
        return cidade;
    }
}
