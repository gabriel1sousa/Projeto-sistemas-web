package com.gestaocursos.controller;

import com.gestaocursos.entity.Aluno;
import com.gestaocursos.repository.AlunoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/alunos")
@Transactional
public class AlunoController {

    private final AlunoRepository repo;

    public AlunoController(AlunoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Aluno> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> buscar(@PathVariable UUID id) {
        Optional<Aluno> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by-email")
    public ResponseEntity<Aluno> buscarPorEmail(@RequestParam String email) {
        return repo.findByEmail(email).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Aluno> criar(@RequestBody Aluno aluno) {
        // se id estiver nulo, o construtor do Aluno já gera UUID
        Aluno salvo = repo.save(aluno);
        return ResponseEntity.created(URI.create("/api/alunos/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable UUID id, @RequestBody Aluno aluno) {
        return repo.findById(id).map(existing -> {
            existing.setNome(aluno.getNome());
            existing.setEmail(aluno.getEmail());
            Aluno atualizado = repo.save(existing);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        return repo.findById(id).map(existing -> {
            repo.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}