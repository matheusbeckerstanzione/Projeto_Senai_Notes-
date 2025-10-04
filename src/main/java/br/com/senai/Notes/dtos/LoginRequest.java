package br.com.senai.Notes.dtos;

import lombok.Data;

// A anotação @Data do Lombok é um atalho poderoso.
// Ela gera automaticamente os métodos getters, setters, toString(),
// equals() e hashCode() para todos os campos da classe.
@Data
public class LoginRequest {

    // Campo para receber o email do usuário.
    private String email;

    // Campo para receber a senha do usuário.
    private String password;
}