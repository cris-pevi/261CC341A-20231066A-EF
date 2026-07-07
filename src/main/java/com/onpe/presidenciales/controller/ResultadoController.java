package com.onpe.presidenciales.controller;

import com.onpe.presidenciales.dto.ResultadoDTO;
import com.onpe.presidenciales.model.Ambito;
import com.onpe.presidenciales.service.ResultadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/resultados")
public class ResultadoController {

    @Autowired
    private ResultadoService resultadoService;

    @GetMapping
    public List<ResultadoDTO> obtenerResultados(@RequestParam Ambito ambito) {
        return resultadoService.obtenerPorAmbito(ambito);
    }

    @GetMapping("/total")
    public List<ResultadoDTO> obtenerTotal() {
        return resultadoService.obtenerTotal();
    }
}