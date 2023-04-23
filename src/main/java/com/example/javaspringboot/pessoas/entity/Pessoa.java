package com.example.javaspringboot.pessoas.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="tb_pessoa")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 255)
    private String nome;

    private LocalDateTime data;

    private long estado_id;
    @ManyToOne
    @JoinColumn(name="estado_id", insertable=false, updatable=false)
    private Estado estado;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "tb_pessoa_perfil",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private List<Perfil> perfis;
}
