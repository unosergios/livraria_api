package br.alura.com.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import br.alura.com.livraria.modelo.Autor;
import br.alura.com.livraria.modelo.Livro;

@ActiveProfiles("test")
class CalculaRoyaltyDoAutorServiceTest {

	private CalculaRoyaltyDoAutorService calculaRoyalty;

	private Livro criaLivroTeste(Integer numeroDePaginas) {
		Livro livro = new Livro(10l,"teste",LocalDate.now(),numeroDePaginas,
				  new Autor(1l,"autor teste","e_mail teste",LocalDate.now(),"minicurr"));
		return livro;
	}	
	
	@BeforeEach
	public void inicializar() {
		calculaRoyalty = new CalculaRoyaltyDoAutorService();
	}
	
	@Test
	void livroComQtdDePaginasMenorIguala100_Dois_Porcento_de_Royalty() {
	  	
      Livro livro = criaLivroTeste(98);
      
      calculaRoyalty = new CalculaRoyaltyDoAutorService();
      Double qtdPaginasdoAutor = calculaRoyalty.calcularRoyaltys(livro);
      
      assertEquals(qtdPaginasdoAutor, 1.96);
	}



	@Test
	void livroComQtdDePaginasMaior100eMenorIguala200Tres_Porcento_de_Royalty() {
	  	
	  Livro livro = criaLivroTeste(200);
      
      //CalculaRoyaltyDoAutorService calculaRoyalty = new CalculaRoyaltyDoAutorService();
      Double qtdPaginasdoAutor = calculaRoyalty.calcularRoyaltys(livro);
      
      assertEquals(qtdPaginasdoAutor, 6.00);
	}	
	
	@Test
	void livroComQtdDePaginasMaior200_Cinco_Porcento_de_Royalty() {
	  	
	  Livro livro = criaLivroTeste(221);
      
 //     CalculaRoyaltyDoAutorService calculaRoyalty = new CalculaRoyaltyDoAutorService();
      Double qtdPaginasdoAutor = calculaRoyalty.calcularRoyaltys(livro);
      
      assertEquals(qtdPaginasdoAutor, 11.05);
	}		
}
