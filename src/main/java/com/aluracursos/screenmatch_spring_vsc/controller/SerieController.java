package com.aluracursos.screenmatch_spring_vsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluracursos.screenmatch_spring_vsc.dto.SerieDTO;
import com.aluracursos.screenmatch_spring_vsc.service.SerieService;

@RestController
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries() {
        return servicio.obtenerTodasLasSeries();

    }

    @GetMapping("/series/mas-evaluadas")
    public List<SerieDTO> obtenerSeriesMasEvaluadas() {
        return servicio.obtenerSeriesMasEvaluadas();
    }
}
