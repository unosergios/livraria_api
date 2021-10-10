package br.alura.com.livraria.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QtdLivrosPorAutorDto {

	private String autor;
	private Long quantidadeLivros;
	private Double percentual;

}
