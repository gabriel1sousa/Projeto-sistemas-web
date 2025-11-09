package com.gestaocursos.controller;

import com.gestaocursos.entity.Aula;
import com.gestaocursos.repository.AulaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/aulas")
@Transactional
public class AulaController {

    private final AulaRepository repo;

    public AulaController(AulaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Aula> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aula> buscar(@PathVariable Long id) {
        Optional<Aula> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/modulo/{moduloId}")
    public List<Aula> listarPorModulo(@PathVariable Long moduloId) {
        return repo.findByModuloId(moduloId);
    }

    @PostMapping
    public ResponseEntity<Aula> criar(@RequestBody Aula aula) {
        Aula salvo = repo.save(aula);
        return ResponseEntity.created(URI.create("/api/aulas/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aula> atualizar(@PathVariable Long id, @RequestBody Aula aula) {
        return repo.findById(id).map(existing -> {
            existing.setTitulo(aula.getTitulo());
            existing.setConteudoResumo(aula.getConteudoResumo());
            existing.setDuracaoMinutos(aula.getDuracaoMinutos());
            existing.setModulo(aula.getModulo());
            Aula atualizado = repo.save(existing);
            return ResponseEntity.ok(atualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        return repo.findById(id).map(existing -> {
            repo.delete(existing);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}