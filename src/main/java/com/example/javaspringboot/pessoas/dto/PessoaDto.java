package com.example.javaspringboot.pessoas.dto;

import com.example.javaspringboot.pessoas.entity.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PessoaDto {
    @NotBlank
    @Size(max = 255)
    private String nome;
    @NotNull
    private LocalDateTime data;
    @NotNull
    private long estado_id;
    @NotEmpty
    private List<Perfil> perfis;
}
