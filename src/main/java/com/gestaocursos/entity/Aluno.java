package com.gestaocursos.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Aluno — PK do tipo UUID (exemplo de ID não numérico).
 */
@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    private UUID id;

    private String nome;

    private String email;

    // relaciona com matriculas (uma lista de matrículas)
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Matricula> matriculas = new HashSet<>();

    public Aluno() {
        // gera UUID automaticamente ao instanciar (alternativa: @PrePersist)
        this.id = UUID.randomUUID();
    }

    public Aluno(String nome, String email) {
        this();
        this.nome = nome;
        this.email = email;
    }

    // getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Matricula> getMatriculas() { return matriculas; }
    public void setMatriculas(Set<Matricula> matriculas) { this.matriculas = matriculas; }
}
