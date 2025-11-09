package com.gestaocursos.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Chave composta para Matricula: combina alunoId (UUID) + turmaId (Long)
 */
@Embeddable
public class MatriculaId implements Serializable {

    private UUID alunoId;
    private Long turmaId;

    public MatriculaId() {}

    public MatriculaId(UUID alunoId, Long turmaId) {
        this.alunoId = alunoId;
        this.turmaId = turmaId;
    }

    // getters e setters
    public UUID getAlunoId() { return alunoId; }
    public void setAlunoId(UUID alunoId) { this.alunoId = alunoId; }

    public Long getTurmaId() { return turmaId; }
    public void setTurmaId(Long turmaId) { this.turmaId = turmaId; }

    // equals e hashCode — obrigatório para chaves compostas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatriculaId)) return false;
        MatriculaId that = (MatriculaId) o;
        return Objects.equals(getAlunoId(), that.getAlunoId()) &&
                Objects.equals(getTurmaId(), that.getTurmaId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAlunoId(), getTurmaId());
    }
}