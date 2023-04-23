package com.example.javaspringboot.pessoas.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tb_pessoa_perfil")
public class PessoaPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long pessoa_id;

    private long perfil_id;
}
