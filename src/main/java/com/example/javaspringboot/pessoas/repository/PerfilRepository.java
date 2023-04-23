package com.example.javaspringboot.pessoas.repository;

import com.example.javaspringboot.pessoas.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
