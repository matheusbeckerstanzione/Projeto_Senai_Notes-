package br.com.senai.Notes.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tag")
public class Tag {

    @Id

    //@GeneratedValue - define que a chave primaria e gerenciada pelo BD
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "tag_id", nullable = false)
    private Integer TagId;

    @Column(name = "titulo", nullable = true, columnDefinition = "TEXT")
    private String titulo;

    @Column(name = "data_edicao", nullable = false)
    private OffsetDateTime dataedicao;

    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime datacriacao;
}