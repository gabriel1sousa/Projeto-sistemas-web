package com.gestaocursos.service;

import com.gestaocursos.entity.Tag;
import com.gestaocursos.exception.ResourceNotFoundException;
import com.gestaocursos.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository repo;

    public TagServiceImpl(TagRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Tag> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Tag buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag não encontrada: " + id));
    }

    @Override
    public List<Tag> pesquisar(String termo) {
        return repo.findByNomeContainingIgnoreCase(termo);
    }

    @Override
    public Tag criar(Tag tag) {
        return repo.save(tag);
    }

    @Override
    public Tag atualizar(Long id, Tag tag) {
        Tag existente = buscarPorId(id);
        existente.setNome(tag.getNome());
        return repo.save(existente);
    }

    @Override
    public void deletar(Long id) {
        Tag existente = buscarPorId(id);
        repo.delete(existente);
    }
}