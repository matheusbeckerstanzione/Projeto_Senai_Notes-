package br.com.senai.Notes.dtos;

import lombok.Data;

@Data
public class ResetSenhaDTO {
    // OPCIONAL - @NotBlank(message = "O campo e-mail é obrigatório.")
    // OPCIONAL - @Email(message = "Formato de e-mail inválido.")
    private String email;
}
