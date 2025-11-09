package com.gestaocursos.service;

import com.gestaocursos.entity.Modulo;

import java.util.List;

public interface ModuloService {
    List<Modulo> listarTodos();
    Modulo buscarPorId(Long id);
    List<Modulo> listarPorCurso(Long cursoId);
    Modulo criar(Modulo modulo);
    Modulo atualizar(Long id, Modulo modulo);
    void deletar(Long id);
}