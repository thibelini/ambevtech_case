package com.ambevtech.core.controller;

import com.ambevtech.app.exception.ServiceException;
import com.ambevtech.core.entity.Cidade;
import com.ambevtech.core.entity.dto.CidadeDTO;
import com.ambevtech.core.entity.dto.FiltroDTO;
import com.ambevtech.core.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "Api Cidades")
@CrossOrigin(origins = "*")
@RestController()
@RequestMapping(path = "cidades")
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService;

    @ApiOperation(value = "Realiza a busca das cidades cadastradas no banco", notes = "Retorna as cidades cadastradas")
    @PostMapping("/filtrar")
    public ResponseEntity<?> buscarCidade(@ApiParam(required = true, value = "FiltroDTO<Cidade>", type = "FiltroDTO<Cidade>") @RequestBody FiltroDTO<Cidade> filtroDTO) throws ServiceException {
        return ResponseEntity.ok(cidadeService.filtrar(filtroDTO));
    }

    @ApiOperation(value = "Realiza o cadastro da cidade no Sistema", notes = "Cadastra as cidades")
    @PostMapping
    public ResponseEntity<?> cadastrarCidade(@ApiParam(required = true, value = "CidadeDTO", type = "CidadeDTO") @Valid @RequestBody CidadeDTO dto) throws ServiceException {
        cidadeService.salvarCidade(dto);
        return ResponseEntity.ok().build();
    }
}
