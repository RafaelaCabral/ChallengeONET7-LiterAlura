package br.com.alura.literalura;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component // Torna o Main um componente Spring
public class Main {

    private final LivroService livroService;
    private final Scanner scanner;

    @Autowired // Injeção do serviço de livros
    public Main(LivroService livroService) {
        this.livroService = livroService;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Buscar livro por título");
        System.out.println("2 - Listar livros registrados");
        System.out.println("3 - Listar nossos autores");
        System.out.println("4 - Listar autores em determinado ano");
        System.out.println("5 - Listar livros por idioma");
        System.out.println("6 - Sair");
    }

    private void buscarLivroPorTitulo() {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine();
        try {
            Livro livro = livroService.buscarLivroPorTitulo(titulo);
            System.out.println("Livro encontrado: " + livro.getTitulo() + " - " + livro.getAutor().getNome());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarLivros() {
        List<Livro> livros = livroService.listarLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            livros.forEach(livro -> System.out.println(livro.getTitulo() + " - " + livro.getAutor().getNome()));
        }
    }

    private void listarAutores() {
        List<Autor> autores = livroService.listarAutoresComLivros();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            autores.forEach(autor -> {
                System.out.println("Autor: " + autor.getNome());
                autor.getLivros().forEach(livro -> System.out.println(" - " + livro.getTitulo()));
            });
        }
    }

    private void listarAutoresPorAno() {
        System.out.print("Digite o ano: ");
        int ano = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        List<Autor> autores = livroService.listarAutoresVivosEmAno(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano " + ano);
        } else {
            autores.forEach(autor -> System.out.println("Autor: " + autor.getNome()));
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.print("Digite o idioma: ");
        String idioma = scanner.nextLine();

        List<Livro> livros = livroService.listarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma " + idioma);
        } else {
            livros.forEach(livro -> System.out.println(livro.getTitulo() + " - " + livro.getAutor().getNome()));
        }
    }

}
