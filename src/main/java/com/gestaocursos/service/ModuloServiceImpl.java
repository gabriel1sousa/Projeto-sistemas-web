package com.gestaocursos.service;

import com.gestaocursos.entity.Modulo;
import com.gestaocursos.exception.ResourceNotFoundException;
import com.gestaocursos.repository.ModuloRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository repo;

    public ModuloServiceImpl(ModuloRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Modulo> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Modulo buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Módulo não encontrado: " + id));
    }

    @Override
    public List<Modulo> listarPorCurso(Long cursoId) {
        return repo.findByCursoId(cursoId);
    }

    @Override
    public Modulo criar(Modulo modulo) {
        return repo.save(modulo);
    }

    @Override
    public Modulo atualizar(Long id, Modulo modulo) {
        Modulo existente = buscarPorId(id);
        existente.setTitulo(modulo.getTitulo());
        existente.setOrdemNoCurso(modulo.getOrdemNoCurso());
        existente.setCurso(modulo.getCurso());
        return repo.save(existente);
    }

    @Override
    public void deletar(Long id) {
        Modulo existente = buscarPorId(id);
        repo.delete(existente);
    }
}