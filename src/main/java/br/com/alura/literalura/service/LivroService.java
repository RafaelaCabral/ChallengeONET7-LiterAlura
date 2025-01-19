package br.com.alura.literalura.service;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.client.GutendexClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private final GutendexClient gutendexClient;
    private final AutorRepository autorRepository;
    private final LivroRepository livroRepository;

    public LivroService(GutendexClient gutendexClient, AutorRepository autorRepository, LivroRepository livroRepository) {
        this.gutendexClient = gutendexClient;
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
    }

    // Buscar livro por título
    public Livro buscarLivroPorTitulo(String titulo) {
        Optional<JsonNode> resultado = gutendexClient.buscarLivroPorTitulo(titulo);

        if (resultado.isEmpty()) {
            throw new RuntimeException("Nenhum livro encontrado com o título: " + titulo);
        }

        JsonNode livroJson = resultado.get().get("results").get(0); // Pegamos o primeiro resultado
        String tituloLivro = livroJson.get("title").asText();
        String idioma = livroJson.get("languages").get(0).asText();
        int numeroDownloads = livroJson.get("download_count").asInt();

        // Processar autor
        JsonNode autorJson = livroJson.get("authors").get(0); // Consideramos apenas o primeiro autor
        String nomeAutor = autorJson.get("name").asText();
        Integer anoNascimento = autorJson.has("birth_year") ? autorJson.get("birth_year").asInt() : null;
        Integer anoFalecimento = autorJson.has("death_year") ? autorJson.get("death_year").asInt() : null;

        // Verificar se o autor já existe no banco
        Optional<Autor> autorExistente = autorRepository.findByNome(nomeAutor);
        Autor autor = autorExistente.orElseGet(() -> autorRepository.save(new Autor(nomeAutor, anoNascimento, anoFalecimento)));

        // Criar e salvar o livro
        Livro livro = new Livro(tituloLivro, idioma, numeroDownloads, autor);
        return livroRepository.save(livro);
    }

    // Listar todos os livros registrados
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    // Listar todos os autores com seus livros registrados
    public List<Autor> listarAutoresComLivros() {
        return autorRepository.findAll();
    }

    // Listar autores vivos em determinado ano
    public List<Autor> listarAutoresVivosEmAno(int ano) {
        return autorRepository.findAutoresByAnoNascimentoBeforeAndAnoFalecimentoAfter(ano, ano);
    }

    // Listar livros por idioma
    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }
}
