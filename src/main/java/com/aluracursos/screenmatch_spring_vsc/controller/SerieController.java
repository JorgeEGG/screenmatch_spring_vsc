package com.aluracursos.screenmatch_spring_vsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluracursos.screenmatch_spring_vsc.dto.SerieDTO;
import com.aluracursos.screenmatch_spring_vsc.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping() // ó @GetMapping("/series") si no estuviera la anotación @RequestMapping("/series") en la clase, pero como ya está, no es necesario colocarla aquí. Cuando esté la anotación @RequestMapping("/series") en la clase, también se puede colocar @GetMapping().
    public List<SerieDTO> obtenerTodasLasSeries() {
        return servicio.obtenerTodasLasSeries();

    }

    @GetMapping("/top5") // Se puede colocar el @GetMapping("/top5") para que la ruta sea /series/top5, pero también se podría colocar @GetMapping("/series/top5") y no sería necesario colocar @RequestMapping("/series") en la clase, pero es más común colocar @RequestMapping("/series") en la clase y luego @GetMapping("/top5") en el método.
    public List<SerieDTO> obtenerTop5() {
        return servicio.obtenerTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosMasRecientes() {
        return servicio.obtenerLanzamientosMasRecientes();
    }
}