package com.aluracursos.screenmatch_spring_vsc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.GetMapping;

import com.aluracursos.screenmatch_spring_vsc.dto.SerieDTO;
import com.aluracursos.screenmatch_spring_vsc.model.SerieFinal;
import com.aluracursos.screenmatch_spring_vsc.repository.SerieFinalRepository;

@Service
public class SerieService {

    @Autowired
    private SerieFinalRepository repository;

    // Ver comentario del método convierteDatos al final de esta clase. Es una forma de evitar repetir código en los dos métodos siguientes, obtenerTodasLasSeries y obtenerTop8, ya que ambos métodos hacen lo mismo pero con diferentes datos, entonces se crea un método que se encargue de convertir los datos y se llama desde ambos métodos.
    // @GetMapping("/series")
    // public List<SerieDTO> obtenerTodasLasSeries() {
    //     return repository.findAll().stream()
    //             .map(s -> new SerieDTO(
    //                     s.getTitulo(),
    //                     s.getTotalDeTemporadas(),
    //                     s.getEvaluacion(),
    //                     s.getPoster(),
    //                     s.getGenero(),
    //                     s.getActores(),
    //                     s.getDuracion(),
    //                     s.getSinopsis()))
    //             .collect(Collectors.toList());
    // }

    // public List<SerieDTO> obtenerTop5() {
    //     return repository.findTop5ByOrderByEvaluacionDesc().stream()
    //             .map(s -> new SerieDTO(
    //                     s.getTitulo(),
    //                     s.getTotalDeTemporadas(),
    //                     s.getEvaluacion(),
    //                     s.getPoster(),
    //                     s.getGenero(),
    //                     s.getActores(),
    //                     s.getDuracion(),
    //                     s.getSinopsis()))
    //             .collect(Collectors.toList());
    // }

    // @GetMapping("/series") // Interesante, se puede colocar el @GetMapping en el servicio, pero es más común colocarlo en el controlador. De esta forma, el servicio se encarga de la lógica de negocio y el controlador se encarga de manejar las solicitudes HTTP.
    public List<SerieDTO> obtenerTodasLasSeries() {
        return convierteDatos(repository.findAll());
    }
    
    public List<SerieDTO> obtenerTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> obtenerLanzamientosMasRecientes() {
        return convierteDatos(repository.lanzamientosMasRecientes());
    }

    // Se puede cambiar el .stream de los primeros métodos anteriores comentados (ObtenerTodasLasSeries y obtenerTop5) por el siguiente:
    public List<SerieDTO> convierteDatos(List<SerieFinal> serie) {
        return serie.stream()
                .map(s -> new SerieDTO(
                        s.getTitulo(),
                        s.getTotalDeTemporadas(),
                        s.getEvaluacion(),
                        s.getPoster(),
                        s.getGenero(),
                        s.getActores(),
                        s.getDuracion(),
                        s.getSinopsis()))
                .collect(Collectors.toList());
    }
}
