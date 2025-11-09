package com.gestaocursos.repository;

import com.gestaocursos.entity.Matricula;
import com.gestaocursos.entity.MatriculaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, MatriculaId> {
    List<Matricula> findByAlunoId(UUID alunoId);
    List<Matricula> findByTurmaId(Long turmaId);

    // buscar matrícula por aluno e turma (útil antes de inserir)
    // Spring Data consegue derivar o método a partir dos nomes das propriedades do EmbeddedId/relacionamentos
    Matricula findByAlunoIdAndTurmaId(UUID alunoId, Long turmaId);
}