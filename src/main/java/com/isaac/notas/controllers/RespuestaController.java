package com.isaac.notas.controllers;


import com.isaac.notas.models.Comentario;
import com.isaac.notas.models.Nota;
import com.isaac.notas.models.Respuesta;
import com.isaac.notas.models.Usuario;
import com.isaac.notas.repositories.ComentarioRepository;
import com.isaac.notas.repositories.NotaRepository;
import com.isaac.notas.repositories.RespuestaRepository;
import com.isaac.notas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/respuesta")
public class RespuestaController {
    @Autowired
    RespuestaRepository respRepository;
    @Autowired
    ComentarioRepository cmntRepository;
    @Autowired
    UsuarioRepository usrRepository;

    @PostMapping("/nuevaRespuesta")
    public ResponseEntity<Void> crearRespuesta(@Validated @RequestBody Respuesta res){
        Optional<Usuario> user = usrRepository.findById(res.getUsuario().getIdUsuario());
        Optional<Comentario> comentario = cmntRepository.findById(res.getComentario().getIdComentario());
        if (user.isPresent() && comentario.isPresent()){
            System.out.println("Si hay un usuario con ese id");
        }
        res.setUsuario(user.get());
        res.setComentario(comentario.get());
        respRepository.save(res);

        return ResponseEntity.ok().build();
    }
}

