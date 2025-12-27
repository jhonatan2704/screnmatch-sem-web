package br.com.alura.screenmatch.service;

import br.com.alura.screenmatch.model.DadosSeries;
import tools.jackson.databind.ObjectMapper;

public class ConverteDados implements IConverteDados{
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterdados(String json, Class<T> classe) {
        return mapper.readValue(json, classe);
    }
}
