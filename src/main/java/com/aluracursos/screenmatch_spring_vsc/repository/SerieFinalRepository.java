package com.aluracursos.screenmatch_spring_vsc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aluracursos.screenmatch_spring_vsc.model.Categoria;
import com.aluracursos.screenmatch_spring_vsc.model.SerieFinal;

public interface SerieFinalRepository extends JpaRepository<SerieFinal, Long>{
    Optional<SerieFinal> findByTituloIgnoreCase(String titulo);
    Optional<SerieFinal> findByTituloContainsIgnoreCase(String nombreSerie);
    List<SerieFinal> findTop5ByOrderByEvaluacionDesc();
    List<SerieFinal> findByGenero(Categoria categoria);
    
}