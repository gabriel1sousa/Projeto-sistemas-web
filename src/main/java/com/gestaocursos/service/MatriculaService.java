package com.gestaocursos.service;

import com.gestaocursos.entity.Matricula;
import com.gestaocursos.entity.MatriculaId;

import java.util.List;
import java.util.UUID;

public interface MatriculaService {
    List<Matricula> listarTodos();
    Matricula buscarPorId(MatriculaId id);
    List<Matricula> listarPorAluno(UUID alunoId);
    List<Matricula> listarPorTurma(Long turmaId);
    Matricula criar(UUID alunoId, Long turmaId);
    void deletar(MatriculaId id);
}
