package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    // Método para encontrar autores vivos em determinado ano
    List<Autor> findAutoresByAnoNascimentoBeforeAndAnoFalecimentoAfter(int anoNascimento, int anoFalecimento);
}
