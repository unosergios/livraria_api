package br.alura.com.livraria.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDto {

	private Long id;
	private String titulo;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataDeLancamento;
	private int numeroDePaginas;
	private AutorDto autor;	
	
	
}
