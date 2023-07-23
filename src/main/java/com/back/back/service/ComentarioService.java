package com.back.back.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.back.back.api.model.Comentario;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ComentarioService {

    public List<Comentario> comentList;

    public ComentarioService() throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper om = new ObjectMapper();
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        om.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        File jsonFile = new File("data.json");

        comentList = om.readValue(jsonFile, new TypeReference<List<Comentario>>() {
        });
    }

    public Optional<Comentario> getComentario(Integer id) {
        Optional optional = Optional.empty();

        for (Comentario comentario : comentList) {
            if (id == comentario.getId()) {
                optional = Optional.of(comentario);
                return optional;
            }
        }
        return optional;
    }

    public List<Comentario> getComentarios() {
        List<Comentario> ListCom = new ArrayList<>();
        for (Comentario comentario : comentList) {

            ListCom.add(comentario);

        }
        return ListCom;
    }

    public Comentario setComentario(Comentario coment) throws IOException {   
        ObjectMapper mapper = new ObjectMapper();
        coment.setId(BuscarId());
        comentList.addAll(Arrays.asList(coment));
        mapper.writeValue(new File("data.json"), comentList);
        return coment;

    }

    public int putPuntaje(Integer id, Integer puntaje) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Comentario comentarioEncontrado = null;

        for (int i = 0; i < comentList.size(); i++) {
            comentarioEncontrado = comentList.get(i).buscarComentarioPorId(id);
            if (comentarioEncontrado != null) {
                break;
            }
        }

        if (comentarioEncontrado != null) {
            comentarioEncontrado.setPuntaje(puntaje);
            ;
            mapper.writeValue(new File("data.json"), comentList);
        }

        return 0;
    }

    public int setSubComentario(Integer id, Comentario subcom) throws IOException {
      
        ObjectMapper mapper = new ObjectMapper();

        Comentario comentarioEncontrado = null;
        subcom.setId(BuscarId());
        for (int i = 0; i < comentList.size(); i++) {
            comentarioEncontrado = comentList.get(i).buscarComentarioPorId(id);
            if (comentarioEncontrado != null) {
                break;
            }
        }

        if (comentarioEncontrado != null) {
            comentarioEncontrado.getSubcomentario().add(subcom);
            mapper.writeValue(new File("data.json"), comentList);
        }

        return 0;
    }

    public int BuscarId() throws IOException {
        int id = 0;
        Comentario comentarioIdMayor = null;

        for (int i = 0; i < comentList.size(); i++) {
            Comentario comentarioEncontrado = comentList.get(i).encontrarComentarioIdMayor();
            if (comentarioIdMayor == null || comentarioEncontrado.getId() > comentarioIdMayor.getId()) {
                comentarioIdMayor = comentarioEncontrado;
            }
        }
        if (comentarioIdMayor != null) {
            id = comentarioIdMayor.getId() + 1;

        }
        return id;
    }

}
