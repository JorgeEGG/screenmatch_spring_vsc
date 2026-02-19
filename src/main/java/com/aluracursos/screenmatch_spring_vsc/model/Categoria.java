package com.aluracursos.screenmatch_spring_vsc.model;

import java.text.Normalizer;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public enum Categoria {
    ACCION("Action", "Acción"),
    ROMANCE("Romance", "Romance"),
    COMEDIA("Comedy", "Comedia"),
    DRAMA("Drama", "Drama"),
    CRIMEN("Crime", "Crimen"),
    ANIMACION("Animation", "Animación"),
    AVENTURA("Adventure", "Aventura");

    private String categoriaOmdb;
    private String categoriaEspanol;

    Categoria(String categoriaOmdb, String categoriaEspanol) {
        this.categoriaOmdb = categoriaOmdb;
        this.categoriaEspanol = categoriaEspanol;
    }

    public static Categoria fromString(String text) {
        var textoNormalizado = normalizar(text);
        for (Categoria categoria : Categoria.values()) {
            if (normalizar(categoria.categoriaOmdb).equals(textoNormalizado)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

//     public String getCategoriaOmdb() {
//         return categoriaOmdb;
//     }

    public static Categoria fromEspanol(String text) {
        var textoNormalizado = normalizar(text);
        for (Categoria categoria : Categoria.values()) {
            if (normalizar(categoria.categoriaEspanol).equals(textoNormalizado)
                    || normalizar(categoria.categoriaOmdb).equals(textoNormalizado)
                    || normalizar(categoria.name()).equals(textoNormalizado)) {
                return categoria;
            }
        }

        for (Categoria categoria : Categoria.values()) {
            if (coincideConCaracterDesconocido(textoNormalizado, normalizar(categoria.categoriaEspanol))
                    || coincideConCaracterDesconocido(textoNormalizado, normalizar(categoria.categoriaOmdb))
                    || coincideConCaracterDesconocido(textoNormalizado, normalizar(categoria.name()))) {
                return categoria;
            }
        }

        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    private static String normalizar(String text) {
        if (text == null) {
            return "";
        }
        var textoReparado = repararTextoCodificado(text).trim();
        var textoSinTildes = Normalizer.normalize(textoReparado, Normalizer.Form.NFD)
                .replaceAll("\\p{M}+", "");
        return textoSinTildes.toUpperCase(Locale.ROOT);
    }

    private static String repararTextoCodificado(String text) {
        if (text.contains("Ã") || text.contains("Â")) {
            return new String(text.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        }
        return text;
    }

    private static boolean coincideConCaracterDesconocido(String entrada, String candidato) {
        if (entrada.length() != candidato.length()) {
            return false;
        }

        for (int i = 0; i < entrada.length(); i++) {
            char caracterEntrada = entrada.charAt(i);
            if (caracterEntrada == '?' || caracterEntrada == '�') {
                continue;
            }
            if (caracterEntrada != candidato.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return categoriaEspanol;
    }
}
