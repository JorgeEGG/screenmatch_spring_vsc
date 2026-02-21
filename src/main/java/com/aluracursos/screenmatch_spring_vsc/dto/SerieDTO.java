package com.aluracursos.screenmatch_spring_vsc.dto;

import com.aluracursos.screenmatch_spring_vsc.model.Categoria;


public record SerieDTO(
    String titulo,
    Integer totalDeTemporadas,
    Double evaluacion,
    String poster,
    Categoria genero,
    String actores,
    String duracion,
    String sinopsis) {

} 