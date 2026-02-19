// -- Active: 1723696354555@@127.0.0.1@3306@mysql_series
package com.aluracursos.screenmatch_spring_vsc.model;

// import java.beans.Transient;  // OJO: Falta hacer la anotación @Transient en el atributo List<EpisodioFinal> pero esta no funciona con JPA
import java.util.List;
import java.util.OptionalDouble;

import jakarta.persistence.*;

// import com.aluracursos.screenmatch_spring_vsc.service.ConsultaGemini;
// import com.aluracursos.screenmatch_spring_vsc.service.ConsultaChatGPT;
@Entity
@Table(name = "series")
public class SerieFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Integer totalDeTemporadas;
    private Double evaluacion;
    private String poster;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String actores;
    private String duracion;
    private String sinopsis;
    // La anotación @Transient se utiliza para indicar que el atributo episodios no debe ser persistido en la base de datos, ya que es una relación que se maneja de forma independiente. Esto es importante para evitar problemas de rendimiento y complejidad al cargar las series desde la base de datos, ya que los episodios pueden ser numerosos y no siempre es necesario cargarlos junto con la serie.
    // @jakarta.persistence.Transient // OJO: la anotación original es @Transient, pero no funciona con JPA, por eso se usa la anotación completa
    @OneToMany(mappedBy = "serieFinal", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // Indica que la relación es bidireccional y que el atributo "serieFinal" en la clase EpisodioFinal es el propietario de la relación
    private List<EpisodioFinal> episodios;

    // public SerieFinal(List<EpisodioFinal> episodios) {
    //     this.episodios = episodios;
    // }

    public SerieFinal() {
    }



    public SerieFinal(DatosSerieFinal datosSerieFinal) {
        this.titulo = datosSerieFinal.titulo();
        this.totalDeTemporadas = datosSerieFinal.totalDeTemporadas();
        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerieFinal.evaluacion())).orElse(0);
        this.poster = datosSerieFinal.poster();
        this.genero = Categoria.fromString(datosSerieFinal.genero().split(",")[0].trim());
        this.actores = datosSerieFinal.actores();
        this.duracion = datosSerieFinal.duracion();
        this.sinopsis = datosSerieFinal.sinopsis();
        // this.sinopsis = ConsultaGemini.obtenerTraduccion(datosSerieFinal.sinopsis());
        // this.sinopsis = ConsultaChatGPT.obtenerTraduccion(datosSerieFinal.sinopsis());
    }

    

    @Override
    public String toString() {
        return 
            //    "\n" +
               "\'" + "titulo: " + titulo + "\'" +
               ", \'" + "genero: " + genero + "\'" +
               ", \'" + "totalDeTemporadas: " + totalDeTemporadas + "\'" +
               ", \'" + "evaluacion: " + evaluacion + "\'" +
               ", \'" + "poster: " + poster + "\'" +
               ", \'" + "actores: " + actores + "\'" +
               ", \'" + "duracion: " + duracion + "\'" +
               ", \'" + "sinopsis: " + sinopsis + "\'" +
               ", \'" + "episodios: " + episodios + "\'"
               ;
    }
    
    public List<EpisodioFinal> getEpisodios() {
        return episodios;
    }
    
    public void setEpisodios(List<EpisodioFinal> episodios) {
        episodios.forEach(episodio -> episodio.setSerieFinal(this)); // Establece la relación bidireccional entre la serie y sus episodios
        this.episodios = episodios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getTotalDeTemporadas() {
        return totalDeTemporadas;
    }

    public void setTotalDeTemporadas(Integer totalDeTemporadas) {
        this.totalDeTemporadas = totalDeTemporadas;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Categoria getGenero() {
        return genero;
    }

    public void setGenero(Categoria genero) {
        this.genero = genero;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    

    
    
}
