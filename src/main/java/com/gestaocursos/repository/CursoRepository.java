package com.gestaocursos.repository;

import com.gestaocursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    // Exemplos de métodos úteis para buscar cursos
    List<Curso> findByTituloContainingIgnoreCase(String termo);
    List<Curso> findByNivel(Curso.Nivel nivel);
}