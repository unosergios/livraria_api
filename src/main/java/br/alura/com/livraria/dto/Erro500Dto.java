package br.alura.com.livraria.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Erro500Dto {

	
	 private LocalDate timestamp;
	 private String erro;
     private String mensagem;
	 private String path;

	
	
}
