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
	
	@Test
	void deveriaRetornarRelatorioDeAutores() {

        Autor a1 = new Autor("André da Silva","e_mail",LocalDate.now(),"curri");
        em.persist(a1);
        
        Autor a2 = new Autor("Fernanda Nogueira","e_mail",LocalDate.now(),"curri");
        em.persist(a2);
        
        Autor a3 = new Autor("Juliana Carvalho","e_mail",LocalDate.now(),"curri");
        em.persist(a3);
        
        Autor a4 = new Autor("Rita de Assis","e_mail",LocalDate.now(),"curri");
        em.persist(a4);        

        Autor a5 = new Autor("Rodrigo De Souza","e_mail",LocalDate.now(),"curri");
        em.persist(a5);    
        
		Livro l1 = new Livro("Aprenda a falar em público",LocalDate.now(),100,a3);
        em.persist(l1);
        
		Livro l2 = new Livro("Aprenda Java em 21 dias",LocalDate.now(),100,a1);
        em.persist(l2);
        
		Livro l3 = new Livro("Como ser mais produtivo",LocalDate.now(),100,a2);
        em.persist(l3);
        
		Livro l4 = new Livro("Aprenda Python em 12 dias",LocalDate.now(),100,a1);
        em.persist(l4);
        
		Livro l5 = new Livro("Como fazer bolos incríveis",LocalDate.now(),100,a4);
        em.persist(l5);
        
		Livro l6 = new Livro("Investindo em ações na bolsa de valores",LocalDate.now(),100,a5);
        em.persist(l6);
        
		Livro l7 = new Livro("Otimizando seu tempo",LocalDate.now(),100,a2);
        em.persist(l7);        
		
				
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
