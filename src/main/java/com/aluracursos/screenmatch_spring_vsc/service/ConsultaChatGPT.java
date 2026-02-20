package com.aluracursos.screenmatch_spring_vsc.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class ConsultaChatGPT {
    public static String obtenerTraduccion(String texto) {
        String apiKey = System.getenv("OPENAI_APIKEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("No se encontró la variable de entorno OPENAI_APIKEY");
        }
        OpenAiService service = new OpenAiService(apiKey);

        CompletionRequest requisicion = CompletionRequest.builder()
                .model("GPT-5 nano")
                .prompt("traduce a español el siguiente texto: " + texto)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var respuesta = service.createCompletion(requisicion);
        return respuesta.getChoices().get(0).getText();
    }
}

