package com.ambevtech.core.controller;

import com.ambevtech.core.service.WeatherAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(path = "tempo")
public class WeatherAPIResource {

    @Autowired
    private WeatherAPIService weatherAPIService;

    @GetMapping
    public ResponseEntity<?> buscarCidade(@RequestParam String nomeCidade) {
        return ResponseEntity.ok(weatherAPIService.buscarCidade(nomeCidade));
    }
}
