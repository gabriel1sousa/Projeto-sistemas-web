package com.gestaocursos.repository;

import com.gestaocursos.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByCursoId(Long cursoId);
    Optional<Turma> findByCodigo(String codigo);
}