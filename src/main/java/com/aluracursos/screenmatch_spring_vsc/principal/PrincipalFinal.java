package com.aluracursos.screenmatch_spring_vsc.principal;

import com.aluracursos.screenmatch_spring_vsc.model.Categoria;
import com.aluracursos.screenmatch_spring_vsc.model.DatosSerieFinal;
import com.aluracursos.screenmatch_spring_vsc.model.DatosTemporadasFinal;
import com.aluracursos.screenmatch_spring_vsc.model.EpisodioFinal;
import com.aluracursos.screenmatch_spring_vsc.model.SerieFinal;
import com.aluracursos.screenmatch_spring_vsc.repository.SerieFinalRepository;
import com.aluracursos.screenmatch_spring_vsc.service.ConsumoAPIFinal;
import com.aluracursos.screenmatch_spring_vsc.service.ConvierteDatosFinal;

import java.nio.charset.StandardCharsets;
import java.util.*;
// import java.util.stream.Collectors; se utiliza cuando se desmarquen los comentarios del método mostrarSeriesBuscadas()
import java.util.stream.Collectors;

public class PrincipalFinal {
    private Scanner teclado = new Scanner(System.in, StandardCharsets.UTF_8);
    private ConsumoAPIFinal consumoAPI = new ConsumoAPIFinal();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=53a418d1&type=series";
    private ConvierteDatosFinal conversor = new ConvierteDatosFinal();
    // private List<DatosSerieFinal> datosSeries = new ArrayList<>(); // Se mantiene
    // esta lista para almacenar los datos de las series buscadas, aunque ahora
    // también se almacenan en la base de datos. Ver método mostrarSeriesBuscadas()
    // para más detalles.
    private SerieFinalRepository repositorio;
    private List<SerieFinal> seriesFinal; // Se mantiene esta lista para almacenar las series obtenidas de la base de
                                          // datos, aunque también se podría obtener directamente del repositorio cada
                                          // vez que se necesite. Ver método mostrarSeriesBuscadas() para más detalles.
    private Optional<SerieFinal> serieBuscada; // Se mantiene esta variable para almacenar la serie buscada por título,
                                               // aunque también se podría obtener directamente del repositorio cada vez
                                               // que se necesite. Ver método buscarSeriesPorTitulo() para más detalles.

    public PrincipalFinal(SerieFinalRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {

        System.out.println(
                "\n¡Bienvenido al sistema de gestión de películas! Aquí puedes buscar tus series favoritas, explorar sus episodios y mantener un registro de las series que has buscado. ¡Disfruta explorando el mundo del entretenimiento!");
        System.out.println(
                "\n¿Quieres buscar una serie? ¡Adelante! Ingresa el nombre de la serie que deseas encontrar y descubre toda la información que tenemos para ti. Si quieres explorar los episodios de una serie específica, también puedes hacerlo. Además, puedes revisar las series que has buscado anteriormente para mantener un registro de tus intereses. ¡Vamos a comenzar!");
        System.out.println(
                "\nRecuerda que puedes salir del sistema en cualquier momento seleccionando la opción '0. Salir'. ¡Gracias por usar nuestro sistema de gestión de películas! ¡Esperamos que disfrutes tu experiencia!");
        System.out.println(
                "\n¡No olvides que puedes volver a este menú en cualquier momento para explorar más series o revisar tus búsquedas anteriores! ¡Diviértete navegando por el mundo del entretenimiento y descubriendo nuevas series para disfrutar!");
        System.out.println();

        var opcion = -1;
        while (opcion != 0) {
            var menu = """

                         MENU MOVIES

                    1. Buscar Series en la web
                    2. Buscar Episodios
                    3. Mostrar Series buscadas
                    4. Buscar Series en la DB por Título
                    5. Top 5 mejores Series
                    6. Buscar Series por Categoría
                    7. Filtrar Series por Temporada y Evaluación
                    8. Buscar Episodios por Título
                    9. Top 5 de Episodios por Serie

                    0. Salir
                    """;
            System.out.println(menu);
            System.out.print("\nPor favor, selecciona una opción del menú para continuar: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consumir el salto de línea después de leer el número
            System.out.println();

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;
                case 2:
                    buscarEpisodioPorSerie();
                    break;
                case 3:
                    mostrarSeriesBuscadas();
                    break;
                case 4:
                    // El código comentado en seguida se puede descomentar para implementarlo,
                    // comentando el método buscarSeriesPorTitulo. También se puede implementar esta
                    // funcionalidad directamente en el case 4 del switch..
                    // System.out.print("Por favor, escribe el título de la serie que deseas buscar:
                    // ");
                    // var tituloSerie = teclado.nextLine();
                    // buscarSeriesPorTitulo(tituloSerie);
                    buscarSeriesPorTitulo();
                    break;
                case 5:
                    // El código comentado en seguida se puede descomentar para implementarlo,
                    // comentando el método buscarTop5Series. También se puede implementar esta
                    // funcionalidad directamente en el case 5 del switch..
                    // List<SerieFinal> top5Series = repositorio.findTop5ByOrderByEvaluacionDesc();
                    // System.out.println("\nLas 5 mejores series según su evaluación son:\n");
                    // top5Series.forEach(s -> {
                    // System.out.println(s);
                    // System.out.println();
                    // });
                    buscarTop5Series();
                    break;
                case 6:
                    // System.out.print("Por favor, escribe el género de las series que deseas
                    // buscar: ");
                    // var genero = teclado.nextLine();
                    // buscarSeriesPorGenero(genero);
                    buscarSeriesPorCategoria();
                    break;
                case 7:
                    // System.out.print("Por favor, escribe el número de temporada para filtrar las
                    // series: ");
                    // var numeroTemporada = teclado.nextInt();
                    // teclado.nextLine(); // Consumir el salto de línea después de leer el número
                    // System.out.print("Por favor, escribe la evaluación mínima para filtrar las
                    // series: ");
                    // var evaluacionMinima = teclado.nextDouble();
                    // teclado.nextLine(); // Consumir el salto de línea después de leer el número
                    filtrarSeriesPorTemporadaYEvaluacion();
                    break;
                case 8:
                    // System.out.print("Por favor, escribe el título del episodio que deseas
                    // buscar: ");
                    // var tituloEpisodio = teclado.nextLine();
                    // buscarEpisodiosPorTitulo(tituloEpisodio);
                    buscarEpisodiosPorTitulo();
                    break;
                case 9:
                    buscarTop5Episodios();
                    break;
                case 0:
                    System.out.println(
                            "\nCerrando la aplicación... Gracias por usar el sistema de gestión de películas.");
                    System.out.println(
                            "Recuerda que siempre puedes volver a explorar el mundo del entretenimiento con nosotros. ¡Esperamos verte pronto para descubrir más series y disfrutar de nuevas experiencias de entretenimiento! ¡Hasta la próxima!\n");
                    break;
                default:
                    System.out.print("\nOpción no válida. Oprime ENTER e intenta de nuevo.");
                    teclado.nextLine(); // Consumir el salto de línea después de leer el número
            }

        }
    }

    private DatosSerieFinal getDatosSerieFinal() {
        System.out.print(
                "Por favor, escribe el nombre de la serie que deseas buscar: ");
        // Busca los datos generales de las series
        var nombreSerie = teclado.nextLine();
        System.out.println("Buscando la serie " + "'" + nombreSerie + "'......\n");
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        // System.out.println(json);
        DatosSerieFinal datos = conversor.obtenerDatos(json, DatosSerieFinal.class);
        return datos;
    }

    private void buscarSerieWeb() {
        DatosSerieFinal datos = getDatosSerieFinal();
        if (datos == null || esResultadoInvalido(datos)) {
            System.out.println(
                    "\nNo se encontró la serie en OMDb o la respuesta fue incompleta. Verifica el nombre e intenta nuevamente.\n");
            return;
        }

        Optional<SerieFinal> serieExistente = repositorio.findByTituloContainsIgnoreCase(datos.titulo().trim());

        if (serieExistente.isPresent()) {
            System.out.println("\nLa serie ya está cargada en la base de datos:\n");
            System.out.println(serieExistente.get());
            System.out.println();
            return;
        }

        SerieFinal serie = new SerieFinal(datos);
        repositorio.save(serie);
        // datosSeries.add(datos);
        System.out.println("\n" + datos + "\n");
    }

    private boolean esResultadoInvalido(DatosSerieFinal datos) {
        return datos.titulo() == null || datos.titulo().isBlank() || "N/A".equalsIgnoreCase(datos.titulo())
                || datos.genero() == null || datos.genero().isBlank() || "N/A".equalsIgnoreCase(datos.genero())
                || datos.totalDeTemporadas() == null;
    }

    private boolean esTemporadaInvalida(DatosTemporadasFinal temporada) {
        return temporada.numero() == null || temporada.episodios() == null || temporada.episodios().isEmpty();
    }

    private void buscarEpisodioPorSerie() {
        // DatosSerieFinal datosSerie = getDatosSerieFinal(); // Este método ya no se
        // utilizará porque los episodios se obtendrán directamente de la base de datos
        mostrarSeriesBuscadas(); // Este método se mantiene para mostrar las series que se han buscado hasta
                                 // ahora, pero ya no se utilizará para obtener los datos de la serie de la que
                                 // se quieren buscar los episodios, sino que se obtendrán directamente de la
                                 // base de datos. Por lo tanto, esta línea de código ya no es necesaria.
        System.out.print("Por favor, escribe el nombre de la serie de la que deseas buscar los episodios: ");
        var nombreSerie = teclado.nextLine();
        Optional<SerieFinal> serieFinal = seriesFinal.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreSerie.toLowerCase()))
                .findFirst();

        if (serieFinal.isPresent()) {
            var serieEncontrada = serieFinal.get();
            System.out.println("Los episodios de la serie " + serieEncontrada.getTitulo() + " son:\n");

            // DatosSerieFinal datosSerie = serieFinal.get().getDatosSerieFinal(); De la
            // misma forma que en los episodios, ya no se utilizarán los datos de la serie
            // obtenidos del API, sino que se utilizarán directamente los datos de la serie
            // obtenidos de la base de datos. Por lo tanto, esta línea de código ya no es
            // necesaria.

            // Busca los datos de las temporadas
            List<DatosTemporadasFinal> temporadas = new ArrayList<>();

            for (int i = 1; i <= serieEncontrada.getTotalDeTemporadas(); i++) {
                var json = consumoAPI.obtenerDatos(
                        URL_BASE + serieEncontrada.getTitulo().replace(" ", "+") + "&Season=" + i + API_KEY);
                DatosTemporadasFinal datosTemporadasFinal = conversor.obtenerDatos(json, DatosTemporadasFinal.class);
                if (datosTemporadasFinal == null || esTemporadaInvalida(datosTemporadasFinal)) {
                    System.out.println("No se encontraron episodios válidos para la temporada " + i + ".");
                    continue;
                }
                temporadas.add(datosTemporadasFinal);
            }

            if (temporadas.isEmpty()) {
                System.out.println("\nNo fue posible obtener episodios válidos desde OMDb para la serie '"
                        + serieEncontrada.getTitulo() + "'.\n");
                return;
            }

            System.out.println();
            // temporadas.forEach(System.out::println);
            // El siguie
            temporadas.forEach(t -> {
                System.out.println(t);
                System.out.println();
            });
            List<EpisodioFinal> episodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream()
                            .map(e -> new EpisodioFinal(t.numero(), e)))
                    .collect(Collectors.toList());

            if (episodios.isEmpty()) {
                System.out.println("\nNo se obtuvieron episodios para guardar de la serie '"
                        + serieEncontrada.getTitulo() + "'.\n");
                return;
            }

            serieEncontrada.setEpisodios(episodios);
            repositorio.save(serieEncontrada); // Se actualiza la serie en la base de datos con los episodios obtenidos
        }

    }

    private void mostrarSeriesBuscadas() {
        // List<SerieFinal> seriesFinal = new ArrayList<>();
        // seriesFinal = datosSeries.stream()
        // .map(d -> new SerieFinal(d))
        // .collect(Collectors.toList());

        // El anterior código se puede reemplazar por el siguiente código, que obtiene
        // las series directamente de la base de datos

        seriesFinal = repositorio.findAll();
        System.out.println("\nAquí están las series que has buscado hasta ahora, ordenadas por género:\n");

        seriesFinal.stream()
                .sorted(Comparator.comparing(SerieFinal::getGenero))
                // .forEach(System.out::println);
                .forEach(s -> {
                    System.out.println(s);
                    System.out.println();
                });
    }

    private void buscarSeriesPorTitulo() {
        System.out.print("Por favor, escribe el nombre de la serie que deseas buscar: ");
        var nombreSerie = teclado.nextLine();
        Optional<SerieFinal> serieBuscada = repositorio.findByTituloContainsIgnoreCase(nombreSerie);

        if (serieBuscada.isPresent()) {
            System.out.println("\nLa serie que has buscado es: \n\n" + serieBuscada.get());
        } else {
            System.out.println("\nNo se ha encontrado ninguna serie con el título '" + nombreSerie
                    + "'. Por favor, intenta de nuevo con otro título.");
        }
    }

    private void buscarTop5Series() {
        List<SerieFinal> top5Series = repositorio.findTop5ByOrderByEvaluacionDesc();
        System.out.println("\nLas 5 mejores series en la base de datos, según su evaluación son:\n");
        // top5Series.forEach(s -> {
        // System.out.println(s);
        // System.out.println();
        // });
        top5Series.forEach(s -> System.out.println("Serie: " + s.getTitulo() + ", Evaluación: " + s.getEvaluacion()));
    }

    private void buscarSeriesPorCategoria() {
        System.out
                .print("\nPor favor, escribe el género de las series que deseas buscar. Las categorías disponibles son "
                        + Arrays.toString(Categoria.values()) + ": ");
        var genero = teclado.nextLine();
        // El siguiente código comentado reemplaza las dos líneas de código que se
        // encuentran debajo. También se puede implementar esta funcionalidad
        // directamente en el método buscarSeriesPorCategoria(), sin necesidad de crear
        // un método aparte para buscar las series por categoría.
        // List<serieFinal> seriesPorCategoria =
        // repositorio.findByGenero(Categoria.fromEspanol(genero)); // También se puede
        // implementar esta funcionalidad directamente en el método
        // buscarSeriesPorCategoria(), sin necesidad de crear un método aparte para
        // buscar las series por categoría.
        try {
            var categoria = Categoria.fromEspanol(genero);
            List<SerieFinal> seriesPorCategoria = repositorio.findByGenero(categoria);
            if (seriesPorCategoria.isEmpty()) {
                System.out.println("\nNo se han encontrado series para la categoría '" + categoria
                        + "'. Intenta nuevamente con otra categoría.");
                return;

            }
            System.out.println("\nLas series que pertenecen al género '" + categoria + "' son:\n");
            seriesPorCategoria
                    .forEach(s -> System.out.println("Serie: " + s.getTitulo() + ", Género: " + s.getGenero()));
        } catch (IllegalArgumentException e) {
            System.out.println("\nNo se encontró la categoría '" + genero + "'. Intenta nuevamente.");
        }
        // seriesPorCategoria.forEach(System.out::println);
    }

    public void filtrarSeriesPorTemporadaYEvaluacion() {
        System.out.print("\nFiltrar series con cuántas temporadas: ");
        var totalDeTemporadas = teclado.nextInt();
        teclado.nextLine(); // Consumir el salto de línea después de leer el número
        System.out.print("Con evaluación a partir de cuál valor: ");
        var evaluacion = teclado.nextDouble();
        teclado.nextLine(); // Consumir el salto de línea después de leer el número

        List<SerieFinal> filtroSeries = repositorio.seriesPorTemporadaYEvaluacion(totalDeTemporadas, evaluacion);
        System.out.println("***** SERIES FILTRADAS POR TEMPORADA Y EVALUACIÓN *****\n");

        // filtroSeries.forEach(s -> System.out.println("Serie: " + s.getTitulo() + ",
        // Temporadas: " + s.getTotalDeTemporadas() + ", Evaluación: " +
        // s.getEvaluacion()));
        if (filtroSeries.isEmpty()) {
            System.out.println("\nNo se han encontrado series que tengan episodios en la temporada " + totalDeTemporadas
                    + " con una evaluación mínima de " + evaluacion + ". Intenta nuevamente con otros criterios.");
        } else {
            System.out.println("\nLas series que tienen episodios en la temporada " + totalDeTemporadas
                    + " con una evaluación mínima de " + evaluacion + " son:\n");
            filtroSeries.forEach(s -> System.out.println("Serie: " + s.getTitulo() + ", Temporadas: "
                    + s.getTotalDeTemporadas() + ", Evaluación: " + s.getEvaluacion()));
        }
    }

    private void buscarEpisodiosPorTitulo() {
        System.out.print("\nPor favor, escribe el título del episodio que deseas buscar: ");
        var tituloEpisodio = teclado.nextLine();
        List<EpisodioFinal> episodiosEncontrados = repositorio.episodiosPorTitulo(tituloEpisodio);

        // for (SerieFinal serie : seriesFinal) {
        // List<EpisodioFinal> episodios = serie.getEpisodios();
        // if (episodios != null) {
        // episodios.stream()
        // .filter(e ->
        // e.getTitulo().toLowerCase().contains(tituloEpisodio.toLowerCase()))
        // .forEach(episodiosEncontrados::add);
        // }
        // }

        if (episodiosEncontrados.isEmpty()) {
            System.out.println("\nNo se han encontrado episodios con el título '" + tituloEpisodio
                    + "'. Intenta nuevamente con otro título.");
        } else {
            System.out.println("\nLos episodios que coinciden con el título '" + tituloEpisodio + "' son:\n");
            episodiosEncontrados
                    .forEach(e -> System.out.printf("Serie: %s, Temporada: %s, Episodio: %s, Evaluación: %s\n",
                            e.getSerieFinal().getTitulo(), e.getTemporada(), e.getNumeroEpisodio(), e.getEvaluacion()));
            episodiosEncontrados.forEach(e -> System.out.println("Serie: " + e.getSerieFinal().getTitulo()
                    + ", Temporada: " + e.getTemporada() + ", Episodio: " + e.getTitulo()));
        }
    }

    private void buscarTop5Episodios() {
        buscarSeriesPorTitulo();

        if (serieBuscada.isPresent()) {
            SerieFinal serie = serieBuscada.get();
            List<EpisodioFinal> topEpisodios = repositorio.top5Episodios(serie);

            if (topEpisodios == null || topEpisodios.isEmpty()) {
                System.out.println("\nNo se han encontrado episodios para la serie '" + serie.getTitulo()
                        + "'. Asegúrate de haber buscado la serie y obtenido sus episodios previamente.");
                return;
            } else {
                System.out.println(
                        "\nNo se ha encontrado la serie que has buscado. Por favor, intenta de nuevo con otro título.");
            }

        }
    }
}
