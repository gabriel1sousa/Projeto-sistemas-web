package com.gestaocursos.controller;

import com.gestaocursos.entity.Matricula;
import com.gestaocursos.entity.MatriculaId;
import com.gestaocursos.entity.Aluno;
import com.gestaocursos.entity.Turma;
import com.gestaocursos.repository.MatriculaRepository;
import com.gestaocursos.repository.AlunoRepository;
import com.gestaocursos.repository.TurmaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/matriculas")
@Transactional
public class MatriculaController {

    private final MatriculaRepository repo;
    private final AlunoRepository alunoRepo;
    private final TurmaRepository turmaRepo;

    public MatriculaController(MatriculaRepository repo, AlunoRepository alunoRepo, TurmaRepository turmaRepo) {
        this.repo = repo;
        this.alunoRepo = alunoRepo;
        this.turmaRepo = turmaRepo;
    }

    // Lista todas (cuidado em produção)
    @GetMapping
    public List<Matricula> listar() {
        return repo.findAll();
    }

    // Busca por chave composta: /api/matriculas/{alunoId}/{turmaId}
    @GetMapping("/{alunoId}/{turmaId}")
    public ResponseEntity<Matricula> buscar(@PathVariable UUID alunoId, @PathVariable Long turmaId) {
        MatriculaId id = new MatriculaId(alunoId, turmaId);
        Optional<Matricula> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Listar por aluno
    @GetMapping("/aluno/{alunoId}")
    public List<Matricula> listarPorAluno(@PathVariable UUID alunoId) {
        return repo.findByAlunoId(alunoId);
    }

    // Listar por turma
    @GetMapping("/turma/{turmaId}")
    public List<Matricula> listarPorTurma(@PathVariable Long turmaId) {
        return repo.findByTurmaId(turmaId);
    }

    // Criar matrícula — payload mínimo: { "alunoId": "...", "turmaId": 1 }
    public static class MatriculaRequest {
        public UUID alunoId;
        public Long turmaId;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody MatriculaRequest req) {
        Optional<Aluno> alunoOpt = alunoRepo.findById(req.alunoId);
        Optional<Turma> turmaOpt = turmaRepo.findById(req.turmaId);

        if (alunoOpt.isEmpty() || turmaOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Aluno ou Turma não encontrados");
        }

        // evita duplicata
        Matricula existing = repo.findByAlunoIdAndTurmaId(req.alunoId, req.turmaId);
        if (existing != null) {
            return ResponseEntity.status(409).body("Matrícula já existe");
        }

        Matricula m = new Matricula();
        m.setAluno(alunoOpt.get());
        m.setTurma(turmaOpt.get());
        m.setId(new MatriculaId(req.alunoId, req.turmaId));
        m.setInscritoEm(LocalDateTime.now());
        Matricula salvo = repo.save(m);
        return ResponseEntity.created(URI.create("/api/matriculas/" + req.alunoId + "/" + req.turmaId)).body(salvo);
    }

    @DeleteMapping("/{alunoId}/{turmaId}")
    public ResponseEntity<Void> deletar(@PathVariable UUID alunoId, @PathVariable Long turmaId) {
        MatriculaId id = new MatriculaId(alunoId, turmaId);
        return repo.findById(id).map(existing -> {
            repo.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}