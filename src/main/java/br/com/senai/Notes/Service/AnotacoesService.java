package br.com.senai.Notes.Service;

import br.com.senai.Notes.Repository.AnotacoesRepository;
import br.com.senai.Notes.Repository.TagRepository;
import br.com.senai.Notes.Repository.UsuarioRepository;
import br.com.senai.Notes.dtos.CadastrarAnotacoesDto;
import br.com.senai.Notes.model.Anotacoes;
import br.com.senai.Notes.model.Tag;
import br.com.senai.Notes.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AnotacoesService {
    private final AnotacoesRepository anotacoesRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagRepository tagRepository;

    public AnotacoesService(AnotacoesRepository repo, UsuarioRepository usuarioRepository, TagRepository tagRepository) {
        this.anotacoesRepository = repo;
        this.usuarioRepository = usuarioRepository;
        this.tagRepository = tagRepository;
    }

    public List<Anotacoes> listarTodos() {
        return anotacoesRepository.findAll();
    }

    public Anotacoes cadastrar(CadastrarAnotacoesDto DTO) {

        Usuario u = usuarioRepository.findById(DTO.getUsuario_id()).orElse(null);

        Tag t = tagRepository.findById(DTO.getTag_id()).orElse(null);

        Anotacoes anotacoes = new Anotacoes();

        anotacoes.setDescricao(DTO.getDescricao());
        anotacoes.setTitulo(DTO.getTitulo());

        anotacoes.setImagem_url(DTO.getImagem_url());
        anotacoes.setDataCriacao(OffsetDateTime.now());
        anotacoes.setDataEdicao(OffsetDateTime.now());
        anotacoes.setUsuario(u);
        anotacoes.setTag(t);

        return anotacoesRepository.save(anotacoes);
    }

    public Anotacoes buscarPorId(Integer id) {
        return anotacoesRepository.findById(id).orElse(null);
    }

    public Anotacoes deletarAnotacoes(Integer id) {
        Anotacoes anotacoes = buscarPorId(id);
        if (anotacoes == null) {
            return null;
        }
        anotacoesRepository.delete(anotacoes);
        return anotacoes;
    }
    public Anotacoes atualizarAnotacoes(Integer id, Anotacoes tNovo) {
        Anotacoes tExistente = buscarPorId(id);

        if (tExistente == null) {
            return null;
        }

        tExistente.setDescricao(tNovo.getDescricao());
        tExistente.setTitulo(tNovo.getTitulo());
        tExistente.setArquivarNotas(tNovo.getArquivarNotas());
        tExistente.setImagem_url(tNovo.getImagem_url());
        tExistente.setDataCriacao(tNovo.getDataCriacao());
        tExistente.setDataEdicao(tNovo.getDataEdicao());
        tExistente.setTag(tNovo.getTag());
        tExistente.setUsuario(tNovo.getUsuario());

        return anotacoesRepository.save(tExistente);
    }

    public List<Anotacoes> AnotacaoPorEmail(String email) {
        return anotacoesRepository.findByUsuarioEmail(email);
    }

}

