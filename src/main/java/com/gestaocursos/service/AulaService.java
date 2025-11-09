package com.gestaocursos.service;

import com.gestaocursos.entity.Aula;

import java.util.List;

public interface AulaService {
    List<Aula> listarTodos();
    Aula buscarPorId(Long id);
    List<Aula> listarPorModulo(Long moduloId);
    Aula criar(Aula aula);
    Aula atualizar(Long id, Aula aula);
    void deletar(Long id);
}