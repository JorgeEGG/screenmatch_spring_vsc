package com.aluracursos.screenmatch_spring_vsc.principal;

// import com.aluracursos.screenmatch_spring_vsc.model.DatosEpisodio;
import com.aluracursos.screenmatch_spring_vsc.model.DatosSerie;
import com.aluracursos.screenmatch_spring_vsc.model.DatosTemporadas;
import com.aluracursos.screenmatch_spring_vsc.model.Episodio;
import com.aluracursos.screenmatch_spring_vsc.service.ConsumoAPI;
import com.aluracursos.screenmatch_spring_vsc.service.ConvierteDatos;

// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=53a418d1&type=series";
    // private final String URL_BASE =
    // "https://api.themoviedb.org/3/search/movie?api_key=";
    // private final String API_KEY = "2a41cf4f32849a7afd2e458e683d84e4&query=";
    // private final String URL_BASE_2 = "&language=es-ES";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu() {
        System.out.print(
                "\nBienvenido al sistema de gestión de películas. Por favor escribe el nombre de la serie que deseas buscar: ");
        // Busca los datos generales de las series
        var nombreSerie = teclado.nextLine();
        System.out.println("Buscando la serie " + "'" + nombreSerie + "'......");
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        // var json = consumoAPI.obtenerDatos(URL_BASE + API_KEY + nombreSerie.replace("
        // ", "+") + URL_BASE_2);
        DatosSerie datos = conversor.obtenerDatos(json, DatosSerie.class);
        // System.out.println("\n" + datos + "\n");
        System.out.println();
        System.out.println("Título: " + datos.titulo());
        System.out.println("Temporadas: " + datos.totalDeTemporadas());
        System.out.println("Evaluación: " + datos.evaluacion());
        System.out.println("Género: " + datos.genero());
        System.out.println("Actores: " + datos.actores());
        System.out.println("Duración: " + datos.duracion());

        // Busca los datos de las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i + API_KEY);
            DatosTemporadas datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        // temporadas.forEach(System.out::println); // Muestra toda la información de
        // las temporadas
        // System.out.println(temporadas.get(0).numero());
        // System.out.println();

        // Mostrar solamente el título de los episodios de todas las temporadas
        // for (int i = 0; i < datos.totalDeTemporadas(); i++) {
        // List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
        // for (int j = 0; j < episodiosTemporada.size(); j++) {
        // System.out.println("Temporada " + (i + 1) + " - Episodio " + (j + 1) + ": " +
        // episodiosTemporada.get(j).titulo());
        // // System.out.printf("Temporada %d - Episodio %d: %s%n", (i + 1), (j + 1),
        // episodiosTemporada.get(j).titulo());
        // // System.out.printf("Temporada %02d - Episodio %02d: %s%n", (i + 1), (j +
        // 1), episodiosTemporada.get(j).titulo());
        // // System.out.println(episodiosTemporada.get(j).titulo());
        // }
        // }

        // Funciones Lambda para mostrar los títulos de los episodios
        // temporadas.forEach(t -> t.episodios().forEach(e ->
        // System.out.println("Temporada " + t.numero() + " - Episodio " +
        // e.numeroEpisodio() + ": " + e.titulo())));
        // temporadas.forEach(t -> t.episodios().forEach(e ->
        // System.out.println(e.titulo())));

        // System.out.println();
        // Convertir toda la información a una lista de tipo DatosEpisodio
        // List<DatosEpisodio> datosEpisodios = temporadas.stream()
        // .flatMap(t -> t.episodios().stream())
        // .collect(Collectors.toList());

        // Top 5 Episodios mejor evaluados
        // System.out.println("Top 5 de episodios mejor evaluados:");
        // datosEpisodios.stream()
        // .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
        // .peek(e -> System.out.println("Primer filtro (N/A): " + e))
        // .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
        // .peek(e -> System.out.println("Segundo filtro (ordenación de Mayor a menor):
        // " + e))
        // .map(e -> e.titulo().toUpperCase())
        // .peek(e -> System.out.println("Tercer filtro (mayúsculas): " + e))
        // .limit(5)
        // .forEach(System.out::println);
        // .forEach(e -> System.out.println("Episodio " + e.numeroEpisodio() + ": " +
        // e.titulo() + " (Evaluación: " + e.evaluacion() + ")"));

        System.out.println();

        // Convirtiendo los datos a una lista de tipo episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d)))
                .collect(Collectors.toList());

        episodios.forEach(System.out::println);

        // Busqueda de episodios a partir de X fecha de lanzamiento
        // System.out.print("\nIndica el año a partir de cual quieres ver el episodio:
        // ");
        // var fecha = teclado.nextLine();
        // teclado.next(); // Limpiar el buffer del teclado

        // LocalDate fechaBusqueda = LocalDate.of(Integer.parseInt(fecha), 1, 1);

        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

        // episodios.stream()
        // .filter(e -> e.getFechaDeLanzamiento() != null &&
        // e.getFechaDeLanzamiento().isAfter(fechaBusqueda));
        // .forEach(e -> System.out.println(
        // "Temporada " + e.getTemporada() +
        // " Episodio " + e.getNumeroEpisodio() + " " + e.getTitulo() +
        // " (Fecha de lanzamiento: " + e.getFechaDeLanzamiento().format(dtf) + ")"));

        // System.out.println();

        // Busca episodios por palabra clave en el título
        // System.out.print("Indica la palabra clave para buscar en el título del
        // episodio: ");
        // var palabraClave = teclado.nextLine();
        // System.out.println();
        // Optional<Episodio> episodioBuscado = episodios.stream()
        // .filter(e ->
        // e.getTitulo().toLowerCase().contains(palabraClave.toLowerCase()))
        // .findFirst();
        // if (episodioBuscado.isPresent()) {
        // Episodio e = episodioBuscado.get();
        // System.out.println("Episodio encontrado: " +
        // "Temporada " + e.getTemporada() +
        // " Episodio " + e.getNumeroEpisodio() + " " + e.getTitulo() +
        // " (Fecha de lanzamiento: " + e.getFechaDeLanzamiento().format(dtf) + ")");
        // } else {
        // System.out.println("No se encontró ningún episodio con la palabra clave '" +
        // palabraClave + "'.");
        // }

        // System.out.println();

        Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0) // Filtra episodios con evaluación válida (mayor que 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));
        // Para imprimi cada temporada en su propia línea, se puede usar el siguiente
        // código:
        System.out.println();
        System.out.println("Evaluación promedio por temporada:");
        evaluacionesPorTemporada.forEach((temporada, evaluacion) -> System.out.println("Temporada " + temporada
                + ": Evaluación promedio = " + String.format(Locale.ROOT, "%.2f", evaluacion)));
        // Para imprimir la salida con el formato deseado, se puede usar el siguiente
        // código:
        System.out.println();
        System.out.println("Evaluación promedio por temporada:");
        System.out.println("Dos decimales: " + evaluacionesPorTemporada.entrySet().stream()
                .map(e -> e.getKey() + "=" + String.format(Locale.ROOT, "%.2f", e.getValue()))
                .collect(Collectors.joining(", ", "{", "}")));
        // Impresión como está en aula, sin formato de decimales
        System.out.println();
        System.out.println("Evaluación promedio por temporada:");
        System.out.println("Todos los decimales: " + evaluacionesPorTemporada);
        System.out.println();

        DoubleSummaryStatistics estadisticasEvaluacion = episodios.stream()
                .filter(e -> e.getEvaluacion() > 0.0) // Filtra episodios con evaluación válida (mayor que 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("Estadísticas de evaluación de episodios:");
        System.out.println(estadisticasEvaluacion);
        System.out.println();
        System.out.println("Número de episodios evaluados: " + estadisticasEvaluacion.getCount());
        System.out.println(
                "Evaluación promedio: " + String.format(Locale.ROOT, "%.2f", estadisticasEvaluacion.getAverage()));
        System.out.println("Evaluación máxima: " + String.format(Locale.ROOT, "%.2f", estadisticasEvaluacion.getMax()));
        System.out.println("Evaluación mínima: " + String.format(Locale.ROOT, "%.2f", estadisticasEvaluacion.getMin()));
        System.out.println(
                "Suma de evaluaciones: " + String.format(Locale.ROOT, "%.2f", estadisticasEvaluacion.getSum()));
        System.out.println();

        System.out.print("¿Deseas realizar otra búsqueda? (S/N): ");
        var respuesta = teclado.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println(
                    "\n----------------------------------------------------------------------------------------------------------------------");
            muestraElMenu();
        } else {
            System.out.println("\nGracias por usar el sistema de gestión de películas. ¡Hasta luego!");
        }
        System.out.println();
    }
}
