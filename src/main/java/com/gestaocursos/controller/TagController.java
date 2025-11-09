package com.gestaocursos.controller;

import com.gestaocursos.entity.Tag;
import com.gestaocursos.repository.TagRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags")
@Transactional
public class TagController {

    private final TagRepository repo;

    public TagController(TagRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Tag> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> buscar(@PathVariable Long id) {
        Optional<Tag> o = repo.findById(id);
        return o.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<Tag> pesquisar(@RequestParam("q") String q) {
        return repo.findByNomeContainingIgnoreCase(q);
    }

    @PostMapping
    public ResponseEntity<Tag> criar(@RequestBody Tag tag) {
        Tag salvo = repo.save(tag);
        return ResponseEntity.created(URI.create("/api/tags/" + salvo.getId())).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> atualizar(@PathVariable Long id, @RequestBody Tag tag) {
        return repo.findById(id).map(existing -> {
            existing.setNome(tag.getNome());
            Tag atualizado = repo.save(existing);
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
