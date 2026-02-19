package com.aluracursos.screenmatch_spring_vsc.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jakarta.persistence.*;

@Entity
@Table(name = "episodios")

public class EpisodioFinal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double evaluacion;
    private LocalDate fechaDeLanzamiento;
    @ManyToOne
    private SerieFinal serieFinal; // Relación con SerieFinal

    public EpisodioFinal() {
    }

    public EpisodioFinal(Integer numero, DatosEpisodioFinal d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf(d.evaluacion().replace("N/A", "0"));
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0;
        }
        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaDeLanzamiento());
        } catch (DateTimeParseException e) {
            this.fechaDeLanzamiento = null;
        }
    }

    public SerieFinal getSerieFinal() {
        return serieFinal;
    }
    
    public void setSerieFinal(SerieFinal serieFinal) {
        this.serieFinal = serieFinal;
    }

    public Integer getTemporada() {
        return temporada;
    }
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }
    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }
    
    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }
    
    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }

    

    @Override
    public String toString() {
        return "temporada " + temporada +
               " Episodio " + numeroEpisodio + 
               ": " + titulo + 
               " (Evaluación: " + evaluacion + 
               " - Fecha de Lanzamiento: " + fechaDeLanzamiento + ")";
    }   
}
