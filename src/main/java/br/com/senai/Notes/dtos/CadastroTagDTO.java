package br.com.senai.Notes.dtos;


import br.com.senai.Notes.model.Usuario;
import lombok.Data;


@Data
public class CadastroTagDTO {
    private Integer id;
    private String nomeCompleto;
    // TODO: Trocar para DTO de Usuario
    private Usuario usuario;

}
