package com.isaac.notas.controllers;

import com.isaac.notas.models.Nota;
import com.isaac.notas.models.Usuario;
import com.isaac.notas.repositories.NotaRepository;
import com.isaac.notas.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/notas")
public class NotasController {
    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/todasNotas")
    public ModelAndView verNotas(){
        List<Nota> notasList = (List<Nota>) notaRepository.findAll();
        return new ModelAndView("notas/notas").addObject("notas",notasList);
    }

    @GetMapping("/nuevaNota")
    public ModelAndView nuevaNota(){
        return new ModelAndView("notas/nueva-nota")
                .addObject("nota",new Nota());
    }

    @PostMapping("/nuevaNota")
    public ModelAndView guardarNota(@Validated Nota nota, @RequestParam("userId") Integer idUser){
        Optional<Usuario> usrOp = usuarioRepository.findById(idUser);
        nota.setUsuario(usrOp.get());

        notaRepository.save(nota);

        return new ModelAndView("redirect:/notas/todasNotas");
    }
}
