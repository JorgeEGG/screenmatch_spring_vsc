package com.aluracursos.screenmatch_spring_vsc.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
