package com.isaac.notas.controllers;

import com.isaac.notas.models.Comentario;
import com.isaac.notas.models.Nota;
import com.isaac.notas.models.Usuario;
import com.isaac.notas.repositories.ComentarioRepository;
import com.isaac.notas.repositories.NotaRepository;
import com.isaac.notas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/coments")
public class ComentarioController {

    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    UsuarioRepository usrRepository;
    @Autowired
    NotaRepository ntaRepository;

    @GetMapping("/todosComentarios")
    public List<Comentario> obtenerComentarios(){
        List<Comentario> comentarios = (List<Comentario>) comentarioRepository.findAll();
        return comentarios;
    }

    @PostMapping("/nuevoComentario")
    public ResponseEntity<Void> crearComentarios(@Validated @RequestBody Comentario coment){
        Optional<Usuario> user = usrRepository.findById(coment.getUsuario().getIdUsuario());
        Optional<Nota> nota = ntaRepository.findById(coment.getNota().getIdNota());
        if (user.isPresent()&&nota.isPresent()){
            System.out.println("Si hay un usuario con ese id");
        }
        coment.setUsuario(user.get());
        coment.setNota(nota.get());
        comentarioRepository.save(coment);

        return ResponseEntity.ok().build();
    }
}
