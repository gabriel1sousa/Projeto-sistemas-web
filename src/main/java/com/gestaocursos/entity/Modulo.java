package com.gestaocursos.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Modulo — pertence a Curso (ManyToOne) e possui várias Aulas (OneToMany)
 */
@Entity
@Table(name = "modulos")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private Integer ordemNoCurso;

    // ManyToOne -> cada módulo pertence a um curso
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // OneToMany -> módulo tem várias aulas
    @OneToMany(mappedBy = "modulo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Aula> aulas = new HashSet<>();

    public Modulo() {}

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Integer getOrdemNoCurso() { return ordemNoCurso; }
    public void setOrdemNoCurso(Integer ordemNoCurso) { this.ordemNoCurso = ordemNoCurso; }

    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

    public Set<Aula> getAulas() { return aulas; }
    public void setAulas(Set<Aula> aulas) { this.aulas = aulas; }
}
