package com.example.javaspringboot.pessoas.repository;

import com.example.javaspringboot.pessoas.entity.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
