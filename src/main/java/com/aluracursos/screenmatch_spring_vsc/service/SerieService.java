package com.aluracursos.screenmatch_spring_vsc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.aluracursos.screenmatch_spring_vsc.dto.SerieDTO;
import com.aluracursos.screenmatch_spring_vsc.repository.SerieFinalRepository;

@Service
public class SerieService {

    @Autowired
    private SerieFinalRepository repository;

    @GetMapping("/series")
    public List<SerieDTO> obtenerTodasLasSeries() {
        return repository.findAll().stream()
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

    public List<SerieDTO> obtenerSeriesMasEvaluadas() {
        return repository.findTop5ByOrderByEvaluacionDesc().stream()
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
