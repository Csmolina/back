package com.back.back.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.back.back.api.model.Comentario;
import com.back.back.service.ComentarioService;
@RestController
public class ComentarioController {
    
    private ComentarioService comentService;

    public ComentarioController(ComentarioService comentService){
        this.comentService=comentService;
    }

    @GetMapping ("/comentario")
    public Comentario getComent(@RequestParam Integer id){
        
        Optional coment= comentService.getComentario(id);
        if(coment.isPresent()){
            return (Comentario) coment.get();
        }
        return null;
    }

 @GetMapping ("/comentarios")
 
 public List<Comentario> getComentarioss(){
        return comentService.getComentarios();
        
    }


    @PostMapping("/comentario")
    public void postComent(@RequestBody Comentario com) throws IOException{
         
        comentService.setComentario(com);
        
    }

    @PutMapping("/comentario")
    public void putPuntaje(@RequestParam Integer id, @RequestParam Integer punt) throws IOException
    {
       comentService.putPuntaje(id, punt);
       
    }

    @PutMapping("/subcomentario")
    public void putSubComentario(@RequestParam Integer id, @RequestBody Comentario com) throws IOException
    {
       comentService.setSubComentario(id, com);
       
    }

}
