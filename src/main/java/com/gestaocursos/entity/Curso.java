package com.gestaocursos.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Curso — PK Long, OneToMany para Modulo, ManyToMany com Tag.
 */
@Entity
@Table(name = "cursos")
public class Curso {

    public enum Nivel { BASICO, INTERMEDIARIO, AVANCADO }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 2000)
    private String descricao;

    private Integer duracaoHoras;

    @Enumerated(EnumType.STRING)
    private Nivel nivel = Nivel.BASICO;

    // OneToMany para módulos (curso possui vários módulos)
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Modulo> modulos = new HashSet<>();

    // ManyToMany com Tag (join table)
    @ManyToMany
    @JoinTable(
            name = "curso_tag",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public Curso() {}

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getDuracaoHoras() { return duracaoHoras; }
    public void setDuracaoHoras(Integer duracaoHoras) { this.duracaoHoras = duracaoHoras; }

    public Nivel getNivel() { return nivel; }
    public void setNivel(Nivel nivel) { this.nivel = nivel; }

    public Set<Modulo> getModulos() { return modulos; }
    public void setModulos(Set<Modulo> modulos) { this.modulos = modulos; }

    public Set<Tag> getTags() { return tags; }
    public void setTags(Set<Tag> tags) { this.tags = tags; }
}