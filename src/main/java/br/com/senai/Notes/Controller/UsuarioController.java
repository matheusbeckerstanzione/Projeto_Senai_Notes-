package br.com.senai.Notes.Controller;

import br.com.senai.Notes.Service.UsuarioService;
import br.com.senai.Notes.dtos.CadastrarUsuarioDto;
import br.com.senai.Notes.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/usuario")


public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;

    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() {
        List<Usuario> Usuario = usuarioService.listarTodos();
        return ResponseEntity.ok(Usuario);}

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable Integer id) {
        Usuario Usuario = usuarioService.buscarPorId(id);

        if (Usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuário não encontrado!");
        }

        return ResponseEntity.ok(Usuario);
    }

    // Inserir Novo
    @PostMapping
    public ResponseEntity<Usuario> inserirUsuario(@RequestBody CadastrarUsuarioDto t) {

        Usuario novoUsuario = usuarioService.cadastrar(t);

        if (novoUsuario == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable Integer id, @RequestBody Usuario Usuario) {
        Usuario UsuarioAtualizado = usuarioService.atualizar(id, Usuario);

        if (UsuarioAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuário não encontrado!");
        }

        return ResponseEntity.ok(UsuarioAtualizado);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarUsuario(@PathVariable Integer id) {
        Usuario deletado = usuarioService.deletar(id);

        if (deletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, usuário não foi encontrado.");
        }

        return ResponseEntity.ok("Tipo de usuário excluído com sucesso!");
    }
}





