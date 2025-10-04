package br.com.senai.Notes.dtos;

import br.com.senai.Notes.model.Usuario;
import lombok.Data;
@Data
public class CadastrarUsuarioDto {

        private String name;
        // TODO: Trocar para DTO de Usuario
        private String email;
        private String password;



}
