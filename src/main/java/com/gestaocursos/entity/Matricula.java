package com.gestaocursos.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Matricula — entidade que associa Aluno <-> Turma com atributos próprios.
 * Usa @EmbeddedId (chave composta) e @MapsId para mapear as relações.
 */
@Entity
@Table(name = "matriculas")
public class Matricula {

    @EmbeddedId
    private MatriculaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("alunoId") // mapeia parte da chave composta
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("turmaId") // mapeia parte da chave composta
    @JoinColumn(name = "turma_id")
    private Turma turma;

    private LocalDateTime inscritoEm;

    private Double notaFinal;

    public Matricula() {}

    public Matricula(Aluno aluno, Turma turma) {
        this.aluno = aluno;
        this.turma = turma;
        this.id = new MatriculaId(aluno.getId(), turma.getId());
        this.inscritoEm = LocalDateTime.now();
    }

    // getters e setters
    public MatriculaId getId() { return id; }
    public void setId(MatriculaId id) { this.id = id; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Turma getTurma() { return turma; }
    public void setTurma(Turma turma) { this.turma = turma; }

    public LocalDateTime getInscritoEm() { return inscritoEm; }
    public void setInscritoEm(LocalDateTime inscritoEm) { this.inscritoEm = inscritoEm; }

    public Double getNotaFinal() { return notaFinal; }
    public void setNotaFinal(Double notaFinal) { this.notaFinal = notaFinal; }
}