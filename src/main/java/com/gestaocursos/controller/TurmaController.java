package com.gestaocursos.controller;

import com.gestaocursos.entity.Turma;
import com.gestaocursos.repository.TurmaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turmas")
@Transactional
public class TurmaController {

    private final TurmaRepository repo;

    public TurmaController(TurmaRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Turma> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscar(@PathVariable Long id) {
        Optional<Turma> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/curso/{cursoId}")
    public List<Turma> listarPorCurso(@PathVariable Long cursoId) {
        return repo.findByCursoId(cursoId);
    }

    @PostMapping
    public ResponseEntity<Turma> criar(@RequestBody Turma turma) {
        Turma salvo = repo.save(turma);
        return ResponseEntity.created(URI.create("/api/turmas/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @RequestBody Turma turma) {
        return repo.findById(id).map(existing -> {
            existing.setCodigo(turma.getCodigo());
            existing.setDataInicio(turma.getDataInicio());
            existing.setDataFim(turma.getDataFim());
            existing.setCurso(turma.getCurso());
            Turma atualizado = repo.save(existing);
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