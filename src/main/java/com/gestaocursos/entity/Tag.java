package com.gestaocursos.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Tag — ManyToMany com Curso (ex.: "Java", "Spring", "Docker")
 */
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToMany(mappedBy = "tags")
    private Set<Curso> cursos = new HashSet<>();

    public Tag() {}

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Set<Curso> getCursos() { return cursos; }
    public void setCursos(Set<Curso> cursos) { this.cursos = cursos; }
}

