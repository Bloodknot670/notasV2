package com.isaac.notas.repositories;

import com.isaac.notas.models.Nota;
import org.springframework.data.repository.CrudRepository;

public interface NotaRepository extends CrudRepository<Nota,Integer> {
}
