package com.gestaocursos.service;

import com.gestaocursos.entity.Curso;
import com.gestaocursos.exception.ResourceNotFoundException;
import com.gestaocursos.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CursoServiceImpl implements CursoService {

    private final CursoRepository repo;

    public CursoServiceImpl(CursoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Curso> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Curso buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado: " + id));
    }

    @Override
    public Curso criar(Curso curso) {
        // validações básicas podem ser adicionadas aqui
        return repo.save(curso);
    }

    @Override
    public Curso atualizar(Long id, Curso curso) {
        Curso existente = buscarPorId(id);
        existente.setTitulo(curso.getTitulo());
        existente.setDescricao(curso.getDescricao());
        existente.setDuracaoHoras(curso.getDuracaoHoras());
        existente.setNivel(curso.getNivel());
        // cuidado com coleções (modulos/tags) — manipule explicitamente se necessário
        return repo.save(existente);
    }

    @Override
    public void deletar(Long id) {
        Curso existente = buscarPorId(id);
        repo.delete(existente);
    }

    @Override
    public List<Curso> buscarPorTitulo(String termo) {
        return repo.findByTituloContainingIgnoreCase(termo);
    }

    @Override
    public List<Curso> buscarPorNivel(Curso.Nivel nivel) {
        return repo.findByNivel(nivel);
    }
}