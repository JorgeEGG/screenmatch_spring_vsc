package com.aluracursos.screenmatch_spring_vsc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aluracursos.screenmatch_spring_vsc.dto.EpisodioDTO;
import com.aluracursos.screenmatch_spring_vsc.dto.SerieDTO;
import com.aluracursos.screenmatch_spring_vsc.service.SerieService;

@RestController
@RequestMapping("/series")
public class SerieController {

    @Autowired
    private SerieService servicio;

    @GetMapping() // ó @GetMapping("/series") si no estuviera la anotación
                  // @RequestMapping("/series") en la clase, pero como ya está, no es necesario
                  // colocarla aquí. Cuando esté la anotación @RequestMapping("/series") en la
                  // clase, también se puede colocar @GetMapping().
    public List<SerieDTO> obtenerTodasLasSeries() {
        return servicio.obtenerTodasLasSeries();

    }

    @GetMapping("/top5") // Se puede colocar el @GetMapping("/top5") para que la ruta sea /series/top5,
                         // pero también se podría colocar @GetMapping("/series/top5") y no sería
                         // necesario colocar @RequestMapping("/series") en la clase, pero es más común
                         // colocar @RequestMapping("/series") en la clase y luego @GetMapping("/top5")
                         // en el método.
    public List<SerieDTO> obtenerTop5() {
        return servicio.obtenerTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosMasRecientes() {
        return servicio.obtenerLanzamientosMasRecientes();
    }

    @GetMapping("/{id}") // Se puede colocar el @GetMapping("/{id}") para que la ruta sea /series/{id},
                         // pero también se podría colocar @GetMapping("/series/{id}") y no sería
                         // necesario colocar @RequestMapping("/series") en la clase, pero es más común
                         // colocar @RequestMapping("/series") en la clase y luego @GetMapping("/{id}")
                         // en el método.
    public SerieDTO obtenerSeriePorId(@PathVariable Long id) {
        return servicio.obtenerSeriePorId(id);
    }

    @GetMapping("/{id}/temporadas/todas") // Se puede colocar el @GetMapping("/{id}/temporadas/todas") para que la ruta
                                          // sea /series/{id}/temporadas/todas, pero también se podría colocar
                                          // @GetMapping("/series/{id}/temporadas/todas") y no sería necesario colocar
                                          // @RequestMapping("/series") en la clase, pero es más común colocar
                                          // @RequestMapping("/series") en la clase y luego
                                          // @GetMapping("/{id}/temporadas/todas") en el método.
    public List<EpisodioDTO> obtenerTodasLasTemporadas(@PathVariable Long id) {
        return servicio.obtenerTodasLasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}") // Se puede colocar el
                                                      // @GetMapping("/{id}/temporadas/{numeroTemporada}") para que la
                                                      // ruta sea /series/{id}/temporadas/{numeroTemporada}, pero
                                                      // también se podría colocar
                                                      // @GetMapping("/series/{id}/temporadas/{numeroTemporada}") y no
                                                      // sería necesario colocar @RequestMapping("/series") en la clase,
                                                      // pero es más común colocar @RequestMapping("/series") en la
                                                      // clase y luego @GetMapping("/{id}/temporadas/{numeroTemporada}")
                                                      // en el método.
    public List<EpisodioDTO> obtenerTemporadasPorNumero(@PathVariable Long id,
            @PathVariable Long numeroTemporada) {
        return servicio.obtenerTemporadasPorNumero(id, numeroTemporada);
    }

    @GetMapping("/categoria/{nombreCategoria}") // Se puede colocar el @GetMapping("/categoria/{nombreCategoria}") para que la ruta sea
                                              // /series/categoria/{nombreCategoria}, pero también se podría colocar
                                              // @GetMapping("/series/categoria/{nombreCategoria}") y no sería necesario
                                              // colocar @RequestMapping("/series") en la clase, pero es más común
                                              // colocar @RequestMapping("/series") en la clase y luego
                                              // @GetMapping("/categoria/{nombreCategoria}") en el método.
    public List<SerieDTO> obtenerSeriesPorCategoria(@PathVariable String nombreCategoria) {
        return servicio.obtenerSeriesPorCategoria(nombreCategoria);
    }
}