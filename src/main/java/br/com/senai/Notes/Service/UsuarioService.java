package br.com.senai.Notes.Service;

import br.com.senai.Notes.dtos.CadastrarUsuarioDto;
import br.com.senai.Notes.dtos.UsuarioListarDTO;
import br.com.senai.Notes.model.Usuario;
import br.com.senai.Notes.Repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository UsuarioRepository;
    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository
                                  UsuarioRepository, PasswordEncoder passwordEncoder) {
        this.UsuarioRepository = UsuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioListarDTO> listarTodos() {
      List<Usuario> us = UsuarioRepository.findAll();

      return us.stream()
              .map(this::converterParaListagemDTO)
              .collect(Collectors.toList());
    }
    private UsuarioListarDTO converterParaListagemDTO(Usuario usuario) {
        Usuario usuarioDTO = new Usuario();

        usuarioDTO.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setName(usuario.getName());

        return usuarioDTO;
    }

    public Usuario buscarPorId(Integer id) {
        return UsuarioRepository.findById(id).orElse(null);
    }

    public Usuario cadastrar(CadastrarUsuarioDto t) {

        String senhaCriptografada = passwordEncoder.encode(t.getPassword());

        t.setPassword(senhaCriptografada);

        Usuario novoUsuario = new Usuario();

        novoUsuario.setEmail(t.getEmail());
        novoUsuario.setPassword(t.getPassword());
        novoUsuario.setName(t.getName());



        return UsuarioRepository.save(novoUsuario);
    }

    public Usuario atualizar(Integer id, Usuario tNovo) {
        Usuario tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        tExistente.setEmail(tNovo.getEmail());
        tExistente.setPassword(tNovo.getPassword());
        tExistente.setName(tNovo.getName());

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
