package com.gestaocursos.service;

import com.gestaocursos.entity.Turma;
import com.gestaocursos.exception.ResourceNotFoundException;
import com.gestaocursos.repository.TurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository repo;

    public TurmaServiceImpl(TurmaRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Turma> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Turma buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada: " + id));
    }

    @Override
    public List<Turma> listarPorCurso(Long cursoId) {
        return repo.findByCursoId(cursoId);
    }

    @Override
    public Optional<Turma> buscarPorCodigo(String codigo) {
        return repo.findByCodigo(codigo);
    }

    @Override
    public Turma criar(Turma turma) {
        return repo.save(turma);
    }

    @Override
    public Turma atualizar(Long id, Turma turma) {
        Turma existente = buscarPorId(id);
        existente.setCodigo(turma.getCodigo());
        existente.setDataInicio(turma.getDataInicio());
        existente.setDataFim(turma.getDataFim());
        existente.setCurso(turma.getCurso());
        return repo.save(existente);
    }

    @Override
    public void deletar(Long id) {
        Turma existente = buscarPorId(id);
        repo.delete(existente);
    }
}