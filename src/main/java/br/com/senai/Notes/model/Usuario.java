package br.com.senai.Notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id

    //@GeneratedValue - define que a chave primaria e gerenciada pelo BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "usuario_id", nullable = false)
    private Integer UsuarioId;


    @Column(name = "nomeCompleto", nullable = true, columnDefinition = "TEXT")
    private String nomeCompleto;

    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "senha", nullable = false, columnDefinition = "TEXT")
    private String senha;

    @Column(name = "data_edicao", nullable = false)
    private OffsetDateTime dataedicao;

    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime datacriacao;
    }

