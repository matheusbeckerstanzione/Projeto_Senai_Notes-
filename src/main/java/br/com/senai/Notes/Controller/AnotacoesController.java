package br.com.senai.Notes.Controller;

import br.com.senai.Notes.Service.AnotacoesService;
import br.com.senai.Notes.model.Anotacoes;
import br.com.senai.Notes.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anotacoes")
public class AnotacoesController {
    private final AnotacoesService anotacoesService;
    public AnotacoesController(AnotacoesService anotacoesService) {
        this.anotacoesService = anotacoesService;
    }

    @GetMapping
    public ResponseEntity<List<Anotacoes>> listarAnotacoes() {
        List<Anotacoes> Anotacoes = anotacoesService.listarTodos();

        return ResponseEntity.ok(Anotacoes);
}

@PostMapping
public ResponseEntity<Anotacoes> cadastrarAnotacoes(@RequestBody Anotacoes anotacoes) {
    anotacoes = anotacoesService.cadastrarAnotacoes(anotacoes);
    return ResponseEntity.status(HttpStatus.CREATED).body(anotacoes);

    }


    @GetMapping ("/{id}")
    public ResponseEntity<?> buscarAnotacoes(@PathVariable Integer id) {
        Anotacoes anotacoes = anotacoesService.buscarPorId(id);

        if (anotacoes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("anotações não encontrada!");
        }

        return ResponseEntity.ok(anotacoes);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deletarAnotacoes(@PathVariable Integer id) {
        Anotacoes deletado = anotacoesService.deletarAnotacoes(id);

        if (deletado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Não foi possível excluir, anotação não foi encontrado.");
        }
        // Oi
        return ResponseEntity.ok("Tipo de anotação excluído com sucesso!");
    }

    @PutMapping  ("/{id}")
    public ResponseEntity<?> atualizarAnotacoes(@PathVariable Integer id, @RequestBody Anotacoes Anotacoes) {
        Anotacoes AnotacoesAtualizado = anotacoesService.atualizarAnotacoes(id, Anotacoes);

        if (AnotacoesAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("anotação não encontrada!");
        }

        return ResponseEntity.ok(AnotacoesAtualizado);
    }
    @GetMapping ("/{Email}")
    public ResponseEntity<?> AnotacaoPorEmail(@PathVariable String Email) {
       Anotacoes anotacoes = (Anotacoes) anotacoesService.AnotacaoPorEmail(Email);

        if (anotacoes == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("anotação não encontrada!");
        }
        return ResponseEntity.ok(anotacoes);
    }

}
