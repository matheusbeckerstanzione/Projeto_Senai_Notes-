package br.com.senai.Notes.Controller;


import br.com.senai.Notes.Service.TagService;
import br.com.senai.Notes.Service.UsuarioService;
import br.com.senai.Notes.model.Tag;
import br.com.senai.Notes.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tag")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;

    }
    @GetMapping
    public ResponseEntity<List<Tag>> listarTag() {
        List<Tag> tag = tagService.listarTodos();

        return ResponseEntity.ok(tag);
    }

    @PostMapping
    public ResponseEntity<Tag> cadastrarTag(@RequestBody Tag tag) {
        tag = tagService.cadastrarTag(tag);
        return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarTag(@PathVariable Integer id) {
        Tag tag = tagService.buscarPorId(id);

        if (tag == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("tag não encontrada!");
        }

        return ResponseEntity.ok(tag);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTag(@PathVariable Integer id) {
        Tag deletado = tagService.deletarTag(id);

        if (deletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, usuário não foi encontrado.");
        }

        return ResponseEntity.ok("Tipo de usuário excluído com sucesso!");
    }
    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarTag(@PathVariable Integer id, @RequestBody Tag tag) {
        Tag tagAtualizado = tagService.atualizarTag(id, tag);

        if (tagAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuário não encontrado!");
        }

        return ResponseEntity.ok(tagAtualizado);
    }

}


