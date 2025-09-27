package br.com.senai.Notes.Controller;


import br.com.senai.Notes.Service.UsuarioService;
import br.com.senai.Notes.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;

    }
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {
        List<Usuario> Usuarios = usuarioService.listarTodos();

        return ResponseEntity.ok(Usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        usuario = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuario(@PathVariable Integer id) {
        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuário não encontrado!");
        }

        return ResponseEntity.ok(usuario);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {
        Usuario deletado = usuarioService.deletarUsuario(id);

        if (deletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, usuário não foi encontrado.");
        }

        return ResponseEntity.ok("Tipo de usuário excluído com sucesso!");
    }
    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario Usuario) {
        Usuario UsuarioAtualizado = usuarioService.atualizarUsuario(id, Usuario);

        if (UsuarioAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuário não encontrado!");
        }

        return ResponseEntity.ok(UsuarioAtualizado);
    }

}


