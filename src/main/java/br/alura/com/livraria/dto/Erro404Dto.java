package br.alura.com.livraria.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro404Dto {

	 private LocalDate timestamp;
	 private String erro;
	 private String path;
	
	
	
}
