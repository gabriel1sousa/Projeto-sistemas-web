package com.gestaocursos.service;

import com.gestaocursos.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> listarTodos();
    Tag buscarPorId(Long id);
    List<Tag> pesquisar(String termo);
    Tag criar(Tag tag);
    Tag atualizar(Long id, Tag tag);
    void deletar(Long id);
}