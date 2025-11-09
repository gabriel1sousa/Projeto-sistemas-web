package com.gestaocursos.controller;

import com.gestaocursos.entity.Modulo;
import com.gestaocursos.repository.ModuloRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modulos")
@Transactional
public class ModuloController {

    private final ModuloRepository repo;

    public ModuloController(ModuloRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Modulo> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> buscar(@PathVariable Long id) {
        Optional<Modulo> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/curso/{cursoId}")
    public List<Modulo> listarPorCurso(@PathVariable Long cursoId) {
        return repo.findByCursoId(cursoId);
    }

    @PostMapping
    public ResponseEntity<Modulo> criar(@RequestBody Modulo modulo) {
        Modulo salvo = repo.save(modulo);
        return ResponseEntity.created(URI.create("/api/modulos/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modulo> atualizar(@PathVariable Long id, @RequestBody Modulo modulo) {
        return repo.findById(id).map(existing -> {
            existing.setTitulo(modulo.getTitulo());
            existing.setOrdemNoCurso(modulo.getOrdemNoCurso());
            existing.setCurso(modulo.getCurso());
            Modulo atualizado = repo.save(existing);
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