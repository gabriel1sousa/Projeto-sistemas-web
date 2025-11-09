package com.gestaocursos.service;

import com.gestaocursos.entity.Aluno;
import com.gestaocursos.exception.ResourceNotFoundException;
import com.gestaocursos.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository repo;

    public AlunoServiceImpl(AlunoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Aluno> listarTodos() {
        return repo.findAll();
    }

    @Override
    public Aluno buscarPorId(UUID id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado: " + id));
    }

    @Override
    public Optional<Aluno> buscarPorEmail(String email) {
        return repo.findByEmail(email);
    }

    @Override
    public Aluno criar(Aluno aluno) {
        // se quiser garantir que não haja duplicata por email, verifique aqui
        return repo.save(aluno);
    }

    @Override
    public Aluno atualizar(UUID id, Aluno aluno) {
        Aluno existente = buscarPorId(id);
        existente.setNome(aluno.getNome());
        existente.setEmail(aluno.getEmail());
        return repo.save(existente);
    }

    @Override
    public void deletar(UUID id) {
        Aluno existente = buscarPorId(id);
        repo.delete(existente);
    }
}