package br.com.senai.Notes.Service;

import br.com.senai.Notes.Repository.TagRepository;
import br.com.senai.Notes.Repository.UsuarioRepository;
import br.com.senai.Notes.dtos.CadastroTagDTO;
import br.com.senai.Notes.model.Tag;
import br.com.senai.Notes.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository repo, TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> listarTodos() {
        return tagRepository.findAll();
    }

    public Tag cadastrarTag(CadastroTagDTO dto){
    Tag tag = new Tag();

    tag.setNomeCompleto(dto.getNomeCompleto());
    tag.setDatacriacao(OffsetDateTime.now());
    tag.setDataedicao(OffsetDateTime.now());

        return tagRepository.save(tag);
    }

    public Tag buscarPorId(Integer id) {
        return tagRepository.findById(id).orElse(null);
    }

    public Tag deletarTag(Integer id) {
        Tag tag = buscarPorId(id);
        if (tag == null) {
            return null;
        }
        tagRepository.delete(tag);
        return tag;
    }
    public Tag atualizarTag(Integer id, Tag tNovo) {
        Tag tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        tExistente.setNomeCompleto(tNovo.getNomeCompleto());

        return tagRepository.save(tExistente);
    }

}