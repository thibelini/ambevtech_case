package com.ambevtech.core.controller;

import com.ambevtech.core.entity.Cidade;
import com.ambevtech.core.entity.dto.CidadeDTO;
import com.ambevtech.core.entity.dto.FiltroDTO;
import com.ambevtech.core.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(maxAge = 3600)
@RestController()
@RequestMapping(path = "cidades")
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService;

    @PostMapping("/filtrar")
    public ResponseEntity<?> buscarCidade(@RequestBody FiltroDTO<Cidade> filtroDTO) {
        return ResponseEntity.ok(cidadeService.filtrar(filtroDTO));
    }

    @PostMapping
    public ResponseEntity<?> cadastrarCidade(@Valid @RequestBody CidadeDTO dto){
        cidadeService.salvarCidade(dto);
        return ResponseEntity.ok().build();
    }
}
