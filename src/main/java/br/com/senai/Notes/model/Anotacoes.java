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

@Table(name = "entregas")
public class Anotacoes {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "anotacoes_id", nullable = false)
    private Integer anotacoesId;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "imagem_url", nullable = false)
    private String imagem_url;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "arquivar_notas", nullable = false)
    private String arquivarNotas;

    @Column(name = "data_criacao", nullable = false)
    private OffsetDateTime dataCriacao;

    @Column(name = "data_edicao", nullable = false)
    private OffsetDateTime dataEdicao;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "tag_id")
    private Tag tag;

}