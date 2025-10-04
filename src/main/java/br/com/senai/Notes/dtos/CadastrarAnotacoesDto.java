package br.com.senai.Notes.dtos;

import lombok.Data;

@Data
public class CadastrarAnotacoesDto {

        private String descricao;
        private String imagem_url;
        private String titulo;
        private Integer usuario_id;
        private Integer tag_id;
}
