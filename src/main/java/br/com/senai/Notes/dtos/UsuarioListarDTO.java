package br.com.senai.Notes.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
@Getter
@Setter
public class UsuarioListarDTO {

    private String email;
    private String password;
    private String name;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataEdicao;

    }
