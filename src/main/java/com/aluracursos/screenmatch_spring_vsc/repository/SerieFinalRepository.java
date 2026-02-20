package com.aluracursos.screenmatch_spring_vsc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.aluracursos.screenmatch_spring_vsc.model.Categoria;
import com.aluracursos.screenmatch_spring_vsc.model.EpisodioFinal;
import com.aluracursos.screenmatch_spring_vsc.model.SerieFinal;

public interface SerieFinalRepository extends JpaRepository<SerieFinal, Long>{
    Optional<SerieFinal> findByTituloContainsIgnoreCase(String nombreSerie);
    List<SerieFinal> findTop5ByOrderByEvaluacionDesc();
    List<SerieFinal> findByGenero(Categoria categoria);

    @Query("SELECT s FROM SerieFinal s WHERE s.totalDeTemporadas <= :totalDeTemporadas AND s.evaluacion >= :evaluacion")
    List<SerieFinal> seriesPorTemporadaYEvaluacion(int totalDeTemporadas, Double evaluacion);

    @Query("SELECT e FROM SerieFinal s JOIN s.episodios e WHERE e.titulo ILIKE %:tituloEpisodio%")
    List<EpisodioFinal> episodiosPorTitulo(String tituloEpisodio);
    
    @Query("SELECT e FROM SerieFinal s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC")
    List<EpisodioFinal> top5Episodios(SerieFinal serie);
}