package br.alura.com.livraria.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QtdLivrosPorAutorDto {

	private String autor;
	private Long quantidadeLivros;
	private Double percentual;

}
