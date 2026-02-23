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

    @Query("SELECT e FROM SerieFinal s JOIN s.episodios e WHERE e.titulo ILIKE %:tituloEpisodio%")  // Episodio más reciente de una serie, por ejemplo, se podría colocar el título del episodio y se buscaría en todas las series, pero también se podría colocar el título de la serie y se buscaría en esa serie, pero en este caso se busca por el título del episodio. El ILIKE es para que la búsqueda sea insensible a mayúsculas y minúsculas.
    List<EpisodioFinal> episodiosPorTitulo(String tituloEpisodio);
    
    // Los siguientes métodos son para obtener los episodios más populares de una serie y los lanzamientos más recientes de las series, respectivamente. El primer método utiliza una consulta JPQL para obtener los episodios de una serie específica ordenados por evaluación de forma descendente y limitados a los 5 primeros. El segundo método utiliza una consulta JPQL para obtener las series ordenadas por la fecha de lanzamiento de sus episodios más recientes, limitando el resultado a las 5 primeras series. Es importante destacar que estas consultas pueden variar dependiendo de la estructura exacta de las entidades y sus relaciones, así como del motor de base de datos utilizado, ya que algunos motores pueden no soportar la cláusula LIMIT en JPQL, en cuyo caso se podría utilizar Pageable o ajustar la consulta según sea necesario.
    // Se trabaja con la conexión a la base de datos utilizando Spring Data JPA, lo que permite definir consultas personalizadas mediante la anotación @Query, facilitando así la obtención de datos específicos sin necesidad de escribir código SQL directamente.
    // También se trabaja conectaando al frontend utilizando el controlador SerieController, que expone los endpoints para obtener todas las series, los top 5 y los lanzamientos más recientes, y el servicio SerieService, que contiene la lógica de negocio para procesar los datos obtenidos del repositorio y convertirlos en DTOs para ser enviados al frontend.
    @Query("SELECT e FROM SerieFinal s JOIN s.episodios e WHERE s = :serie ORDER BY e.evaluacion DESC LIMIT 5")
    List<EpisodioFinal> top5Episodios(SerieFinal serie);

    @Query("SELECT s FROM SerieFinal s JOIN s.episodios e GROUP BY s ORDER BY MAX(e.fechaDeLanzamiento) DESC LIMIT 5")
    List<SerieFinal> lanzamientosMasRecientes();

    @Query("SELECT e FROM SerieFinal s JOIN s.episodios e WHERE s.id = :id AND e.temporada = :numeroTemporada")
    List<EpisodioFinal> obtenerTemporadasPorNumero(Long id, Long numeroTemporada);
}