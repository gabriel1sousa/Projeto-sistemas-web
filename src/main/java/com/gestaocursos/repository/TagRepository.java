package com.gestaocursos.repository;

import com.gestaocursos.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByNomeContainingIgnoreCase(String nome);
}