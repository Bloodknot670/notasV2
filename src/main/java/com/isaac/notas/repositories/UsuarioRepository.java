package com.isaac.notas.repositories;

import com.isaac.notas.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {
}
