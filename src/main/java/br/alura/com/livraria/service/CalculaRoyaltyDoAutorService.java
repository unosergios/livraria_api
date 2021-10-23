package br.alura.com.livraria.service;

import br.alura.com.livraria.modelo.Livro;

public class CalculaRoyaltyDoAutorService {
	
// calculo do royalty será com base na qtde de paginas
// livro com menos de 100 paginas - 2% das paginas sobre o preco do livros são do autor
// livro com mais de 100 paginas e menos que 200 paginas - 3%
// livro com mais de 200 paginas - 5 %	
	

   
   public Double calcularRoyaltys(Livro livro) {
	   if (livro.getNumeroDePaginas()<=100) {
		   return livro.getNumeroDePaginas()*2./100.;
	   }else if (livro.getNumeroDePaginas()>100 && livro.getNumeroDePaginas() <=200) {
		         return  livro.getNumeroDePaginas()*3./100.; }
		     else return livro.getNumeroDePaginas()*5./100.;
	  
   }
}
