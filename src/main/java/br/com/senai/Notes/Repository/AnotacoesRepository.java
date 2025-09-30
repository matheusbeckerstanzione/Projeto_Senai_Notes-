package br.com.senai.Notes.Repository;

import br.com.senai.Notes.model.Anotacoes;
import br.com.senai.Notes.model.Tag;
import br.com.senai.Notes.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnotacoesRepository extends JpaRepository<Anotacoes, Integer> {
    List<Usuario> findByUsuarioEmail(String email);
}



