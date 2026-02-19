package com.aluracursos.screenmatch_spring_vsc.principal;

import java.util.Arrays;
import java.util.List;

public class EjemploStreams {
    
    public void muestraEjemplo() {
        List<String> nombres = Arrays.asList( "Ana", "Carlos", "Beatriz", "David", "Sebastián", "Jorge", "Fernando", "Juan Carlos", "Santiago");
        System.out.println();
        nombres.stream()
                .sorted()
                // .limit(5)
                // .filter(nombre -> nombre.startsWith("S"))
                .map(n -> n.toUpperCase())
                // .forEach(System.out::println);
                .forEach(n -> System.out.println("¡Hola " + n + "!"));
        System.out.println();
    }
}
