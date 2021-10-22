package br.alura.com.livraria.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.alura.com.livraria.dto.QtdLivrosPorAutorDto;
import br.alura.com.livraria.modelo.Autor;
import br.alura.com.livraria.modelo.Livro;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@ActiveProfiles("test")
class AutorRepositoryTest {

	@Autowired
	private AutorRepository repository;
	
	@Autowired
	private TestEntityManager em;

	
	private Autor criarUsuario(String nomeAutor) {
		Autor a1 = new Autor(nomeAutor,"e_mail",LocalDate.now(),"curri");
        em.persist(a1);
		return a1;
	}
	
	private void criarLivro(String tituloDoLivro, String nomeDoAutor) {
		Autor autor = criarUsuario(nomeDoAutor);
		Livro l1 = new Livro(tituloDoLivro,LocalDate.now(),100,autor);
        em.persist(l1);
	}

	
	@Test
	void deveriaRetornarRelatorioDeAutores() {
        
		criarLivro("Aprenda a falar em público",
				"Juliana Carvalho");
		
		criarLivro("Aprenda Java em 21 dias",
				"André da Silva");

		criarLivro("Aprenda Python em 12 dias",
				"André da Silva");		
        
		criarLivro("Como ser mais produtivo",
				"Fernanda Nogueira");		
        		
		criarLivro("Como fazer bolos incríveis",
				"Rita de Assis");		
		
		criarLivro("Investindo em ações na bolsa de valores",
				"Rodrigo De Souza");	
		
		criarLivro("Otimizando seu tempo",
				"Fernanda Nogueira");			
	
				
		List<QtdLivrosPorAutorDto> relatorio = repository.relatorioDeAutores();
//	    assertNotNull(relatorio);
//	    assertTrue(relatorio.isEmpty());   
	    assertEquals(5, relatorio.size());
		
		Assertions.assertThat(relatorio)
		  .hasSize(5)
		  .extracting(QtdLivrosPorAutorDto::getAutor,
				      QtdLivrosPorAutorDto::getQuantidadeLivros,
				      QtdLivrosPorAutorDto::getPercentual)
		  .containsExactlyInAnyOrder(
				  Assertions.tuple("André da Silva",2l,28.57),
				  Assertions.tuple("Fernanda Nogueira",2l,28.57),
				  Assertions.tuple("Juliana Carvalho",1l,14.29),
				  Assertions.tuple("Rita de Assis",1l,14.29),
				  Assertions.tuple("Rodrigo De Souza",1l,14.29)
				  );
		}






}
