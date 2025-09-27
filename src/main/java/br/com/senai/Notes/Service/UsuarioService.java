package br.com.senai.Notes.Service;

import br.com.senai.Notes.Repository.UsuarioRepository;
import br.com.senai.Notes.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository repo) {
        this.usuarioRepository = repo;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }


    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario deletarUsuario(Integer id) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null) {
            return null;
        }
        usuarioRepository.delete(usuario);
        return usuario;
    }
    public Usuario atualizarUsuario(Integer id, Usuario tNovo) {
        Usuario tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        tExistente.setEmail(tNovo.getEmail());
        tExistente.setSenha(tNovo.getSenha());
        tExistente.setNomeCompleto(tNovo.getNomeCompleto());

        return usuarioRepository.save(tExistente);
    }

}
