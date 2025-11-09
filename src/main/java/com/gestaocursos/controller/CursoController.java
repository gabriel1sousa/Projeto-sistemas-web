package com.gestaocursos.controller;

import com.gestaocursos.entity.Curso;
import com.gestaocursos.repository.CursoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
@Transactional
public class CursoController {

    private final CursoRepository repo;

    public CursoController(CursoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Curso> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscar(@PathVariable Long id) {
        Optional<Curso> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        Curso salvo = repo.save(curso);
        return ResponseEntity.created(URI.create("/api/cursos/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        return repo.findById(id).map(existing -> {
            existing.setTitulo(curso.getTitulo());
            existing.setDescricao(curso.getDescricao());
            existing.setDuracaoHoras(curso.getDuracaoHoras());
            existing.setNivel(curso.getNivel());
            // não sobrescrever coleções automaticamente aqui (opcional)
            Curso atualizado = repo.save(existing);
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

    // busca por título (exemplo de query derivada)
    @GetMapping("/search")
    public List<Curso> buscarPorTitulo(@RequestParam("q") String termo) {
        return repo.findByTituloContainingIgnoreCase(termo);
    }
}