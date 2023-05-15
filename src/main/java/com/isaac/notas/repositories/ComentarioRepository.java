package com.isaac.notas.repositories;

import com.isaac.notas.models.Comentario;
import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepository extends CrudRepository<Comentario,Integer> {
}
