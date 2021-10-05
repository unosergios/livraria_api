package br.alura.com.livraria.dto;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroFormDto {

	@NotNull
	@Size(min = 10)
	private String titulo;

	@NotNull
	@PastOrPresent
	private LocalDate dataDeLancamento;

	@Min(100)
	private int numeroDePaginas;

 	private String autor;

}
