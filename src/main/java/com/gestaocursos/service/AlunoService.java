package com.gestaocursos.service;

import com.gestaocursos.entity.Aluno;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlunoService {
    List<Aluno> listarTodos();
    Aluno buscarPorId(UUID id);
    Optional<Aluno> buscarPorEmail(String email);
    Aluno criar(Aluno aluno);
    Aluno atualizar(UUID id, Aluno aluno);
    void deletar(UUID id);
}