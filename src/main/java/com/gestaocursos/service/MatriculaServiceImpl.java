package com.gestaocursos.service;


import com.gestaocursos.entity.*;
import com.gestaocursos.exception.ResourceNotFoundException;
import com.gestaocursos.repository.MatriculaRepository;
import com.gestaocursos.repository.AlunoRepository;
import com.gestaocursos.repository.TurmaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository repo;
    private final AlunoRepository alunoRepo;
    private final TurmaRepository turmaRepo;

    public MatriculaServiceImpl(MatriculaRepository repo, AlunoRepository alunoRepo, TurmaRepository turmaRepo) {
        this.repo = repo;
        this.alunoRepo = alunoRepo;
        this.turmaRepo = turmaRepo;
    }

    @Override
    public List<Matricula> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Matricula buscarPorId(MatriculaId id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Matrícula não encontrada: " + id));
    }

    @Override
    public List<Matricula> listarPorAluno(UUID alunoId) {
        return repo.findByAlunoId(alunoId);
    }

    @Override
    public List<Matricula> listarPorTurma(Long turmaId) {
        return repo.findByTurmaId(turmaId);
    }

    @Override
    public Matricula criar(UUID alunoId, Long turmaId) {
        Aluno aluno = alunoRepo.findById(alunoId).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado: " + alunoId));
        Turma turma = turmaRepo.findById(turmaId).orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada: " + turmaId));

        // evita duplicata
        Matricula existing = repo.findByAlunoIdAndTurmaId(alunoId, turmaId);
        if (existing != null) {
            throw new IllegalStateException("Matrícula já existe para aluno " + alunoId + " e turma " + turmaId);
        }

        Matricula m = new Matricula();
        m.setAluno(aluno);
        m.setTurma(turma);
        m.setId(new MatriculaId(alunoId, turmaId));
        m.setInscritoEm(LocalDateTime.now());
        return repo.save(m);
    }

    @Override
    public void deletar(MatriculaId id) {
        Matricula existente = buscarPorId(id);
        repo.delete(existente);
    }
}