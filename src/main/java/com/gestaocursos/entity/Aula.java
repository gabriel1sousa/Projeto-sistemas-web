package com.gestaocursos.entity;

import jakarta.persistence.*;

/**
 * Aula — ManyToOne para Modulo (cada aula pertence a um módulo)
 */
@Entity
@Table(name = "aulas")
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 2000)
    private String conteudoResumo;

    private Integer duracaoMinutos;

    // referencia ao modulo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modulo_id")
    private Modulo modulo;

    public Aula() {}

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getConteudoResumo() { return conteudoResumo; }
    public void setConteudoResumo(String conteudoResumo) { this.conteudoResumo = conteudoResumo; }

    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    public Modulo getModulo() { return modulo; }
    public void setModulo(Modulo modulo) { this.modulo = modulo; }
}
