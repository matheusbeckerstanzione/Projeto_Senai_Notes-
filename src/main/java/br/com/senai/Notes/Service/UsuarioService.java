package br.com.senai.Notes.Service;

import br.com.senai.Notes.dtos.CadastrarUsuarioDto;
import br.com.senai.Notes.model.Usuario;
import br.com.senai.Notes.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository UsuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository
                                  UsuarioRepository, PasswordEncoder passwordEncoder) {
        this.UsuarioRepository = UsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> listarTodos() {
        return UsuarioRepository.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return UsuarioRepository.findById(id).orElse(null);
    }

    public Usuario cadastrar(CadastrarUsuarioDto t) {

        String senhaCriptografada = passwordEncoder.encode(t.getSenha());

        // Substitui a senha original pelo hash gerado
        t.setSenha(senhaCriptografada);

        Usuario novoUsuario = new Usuario();

        novoUsuario.setEmail(t.getEmail());
        novoUsuario.setSenha(t.getSenha());
        novoUsuario.setNomeCompleto(t.getNomeCompleto());



        return UsuarioRepository.save(novoUsuario);
    }

    public Usuario atualizar(Integer id, Usuario tNovo) {
        Usuario tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        tExistente.setEmail(tNovo.getEmail());
        tExistente.setSenha(tNovo.getSenha());
        tExistente.setNomeCompleto(tNovo.getNomeCompleto());

        return UsuarioRepository.save(tExistente);
    }

    public Usuario deletar(Integer id) {
        Usuario tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        UsuarioRepository.delete(tExistente);
        return tExistente;
    }



}
