package com.aluracursos.screenmatch_spring_vsc.service;

public interface IConvierteDatosFinal {
    <T> T obtenerDatos(String json, Class<T> clase);
}
