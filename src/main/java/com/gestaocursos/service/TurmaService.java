package com.gestaocursos.service;

import com.gestaocursos.entity.Turma;

import java.util.List;
import java.util.Optional;

public interface TurmaService {
    List<Turma> listarTodos();
    Turma buscarPorId(Long id);
    List<Turma> listarPorCurso(Long cursoId);
    Optional<Turma> buscarPorCodigo(String codigo);
    Turma criar(Turma turma);
    Turma atualizar(Long id, Turma turma);
    void deletar(Long id);
}