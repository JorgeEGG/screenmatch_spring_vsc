package com.aluracursos.screenmatch_spring_vsc;

// import java.util.ArrayList;
// import java.util.List;

// import com.aluracursos.screenmatch_spring_vsc.principal.Principal; ***** Esta línea *****
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.aluracursos.screenmatch_spring_vsc.principal.EjemploStreams;
// import com.aluracursos.screenmatch_spring_vsc.model.DatosEpisodio;
// import com.aluracursos.screenmatch_spring_vsc.model.DatosSerie;
// import com.aluracursos.screenmatch_spring_vsc.model.DatosTemporadas;
// import com.aluracursos.screenmatch_spring_vsc.service.ConsumoAPI;
// import com.aluracursos.screenmatch_spring_vsc.service.ConvierteDatos;

@SpringBootApplication
public class ScreenmatchSpringVscApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchSpringVscApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception { 

        // Principal principal = new Principal(); ***** Esta línea *****
        // principal.muestraElMenu(); ***** Esta línea *****

        // El siguiente código es para probar el consumo de la API de OMDB
        // Fue reemplazado por las dos líneas anteriores que llaman a la clase Principal y su método muestraElMenu()
        // Se comentaron las importaciones relacionadas al código comentado abajo

        // var consumoAPI = new ConsumoAPI();
        // // var json = consumoAPI.obtenerDatos("https://coffee.alexflipnote.dev/random.json");  // Para obtener una imagen de café
        // var json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&apikey=53a418d1&type=series");    // Obtiene toda la información de la serie Game of Thrones
        // System.out.println("\nJson:" + json);
        // ConvierteDatos conversor = new ConvierteDatos();
        // var datos = conversor.obtenerDatos(json, DatosSerie.class);
        // System.out.println("\n" + datos + "\n");
        // System.out.println("Título: " + datos.titulo());
        // System.out.println("Temporadas: " + datos.totalDeTemporadas());
        // System.out.println("Evaluación: " + datos.evaluacion());
        // System.out.println("Género: " + datos.genero());
        // System.out.println("Actores: " + datos.actores());
        // System.out.println("Duración: " + datos.duracion());
     
        // json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=53a418d1&type=series");    // Obtiene la información sobre temporada y episodios de la serie Game of Thrones
        // DatosEpisodio episodios = conversor.obtenerDatos(json, DatosEpisodio.class);
        // System.out.println("\n" + episodios + "\n");
        // System.out.println("Título episodio: " + episodios.titulo());
        // System.out.println("Número de episodio: " + episodios.numeroEpisodio());
        // System.out.println("Evaluación: " + episodios.evaluacion());
        // System.out.println("Fecha de lanzamiento: " + episodios.fechaDeLanzamiento());
        // System.out.println();

        // List<DatosTemporadas> temporadas =  new ArrayList<>();
        // for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
        //     json = consumoAPI.obtenerDatos("http://www.omdbapi.com/?t=game+of+thrones&Season=" + i + "&apikey=53a418d1&type=series");
        //     var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
        //     temporadas.add(datosTemporadas);
        // }
        // temporadas.forEach(System.out::println);
        // System.out.println(temporadas.get(0).numero());

        // // Contexto para probar EjemploStreams
        // EjemploStreams ejemploStreams = new EjemploStreams();
        // ejemploStreams.muestraEjemplo();
        
    }
} 
