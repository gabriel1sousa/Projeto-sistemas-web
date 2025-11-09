package com.gestaocursos.service;

import com.gestaocursos.entity.Aula;
import com.gestaocursos.exception.ResourceNotFoundException;
import com.gestaocursos.repository.AulaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AulaServiceImpl implements AulaService {

    private final AulaRepository repo;

    public AulaServiceImpl(AulaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Aula> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Aula buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aula não encontrada: " + id));
    }

    @Override
    public List<Aula> listarPorModulo(Long moduloId) {
        return repo.findByModuloId(moduloId);
    }

    @Override
    public Aula criar(Aula aula) {
        return repo.save(aula);
    }

    @Override
    public Aula atualizar(Long id, Aula aula) {
        Aula existente = buscarPorId(id);
        existente.setTitulo(aula.getTitulo());
        existente.setConteudoResumo(aula.getConteudoResumo());
        existente.setDuracaoMinutos(aula.getDuracaoMinutos());
        existente.setModulo(aula.getModulo());
        return repo.save(existente);
    }

    @Override
    public void deletar(Long id) {
        Aula existente = buscarPorId(id);
        repo.delete(existente);
    }
}