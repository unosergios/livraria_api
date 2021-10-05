package br.alura.com.livraria.modelo;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Livro {


	private String titulo;
	private LocalDate dataDeLancamento;
	private int numeroDePaginas;
	private Autor autor;
	
	
}
