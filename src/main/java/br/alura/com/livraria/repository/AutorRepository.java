package br.alura.com.livraria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.alura.com.livraria.dto.QtdLivrosPorAutorDto;
import br.alura.com.livraria.modelo.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

	@Query("select new br.alura.com.livraria.dto.QtdLivrosPorAutorDto ( a.nome,count(l) as quantidadedeLivros,"
			+ "round((count(l) * 1.0/ (select count(l2) from Livro l2)*1.0)*100,2)  as percentual )"
			+ " from Livro l join l.autor a group by a.nome")
	List<QtdLivrosPorAutorDto> relatorioDeAutores();

	
	


	
}
