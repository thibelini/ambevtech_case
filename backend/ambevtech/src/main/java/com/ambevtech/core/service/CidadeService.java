package com.ambevtech.core.service;

import com.ambevtech.app.exception.EnumErrorException;
import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.app.util.Fn;
import com.ambevtech.core.entity.Cidade;
import com.ambevtech.core.entity.dto.CidadeDTO;
import com.ambevtech.core.entity.dto.FiltroDTO;
import com.ambevtech.core.repository.CidadeRepository;
import org.modelmapper.ModelMapper;
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

    public void salvarCidade(CidadeDTO dto){
        validar(dto);
        salvar(dto);
    }

    private void validar(CidadeDTO dto) {
       if (dto == null) {
           throw new ServiceException(EnumErrorException.PARAMETROS_INVALIDOS);
        }

       if (Fn.isEmpty(dto.getLatitude()) || Fn.isEmpty(dto.getLongitude())) {
           throw new ServiceException(EnumErrorException.PARAMETROS_INVALIDOS, "A Latitude e Longitude são campos obrigatórios");
       }

       Cidade cidadeValida = buscarPorCodigoCidade(dto.getCodigoCidade());
       if (cidadeValida != null) {
           throw new ServiceException(EnumErrorException.CIDADE_CADASTRADA);
       }
    }

    private void salvar(CidadeDTO dto) {
        Cidade cidade = new Cidade();
        modelMapper.map(dto, cidade);
        try {
            cidade = cidadeRepository.save(cidade);
        } catch (Exception e) {
            throw new ServiceException(EnumErrorException.FALHA_SALVAR, "Erro ao Salvar a Cidade");
        }
        modelMapper.map(cidade, CidadeDTO.class);
    }

    private Cidade buscarPorCodigoCidade(Integer codigoCidade) {
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
