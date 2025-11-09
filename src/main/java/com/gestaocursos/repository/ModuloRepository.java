package com.gestaocursos.repository;

import com.gestaocursos.entity.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuloRepository extends JpaRepository<Modulo, Long> {
    List<Modulo> findByCursoId(Long cursoId);
    List<Modulo> findByOrdemNoCurso(Integer ordem);
}