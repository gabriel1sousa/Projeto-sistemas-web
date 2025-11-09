package com.gestaocursos.service;

import com.gestaocursos.entity.Curso;

import java.util.List;

public interface CursoService {
    List<Curso> listarTodos();
    Curso buscarPorId(Long id);
    Curso criar(Curso curso);
    Curso atualizar(Long id, Curso curso);
    void deletar(Long id);
    List<Curso> buscarPorTitulo(String termo);
    List<Curso> buscarPorNivel(Curso.Nivel nivel);
}
