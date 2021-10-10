package br.alura.com.livraria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.alura.com.livraria.dto.QtdLivrosPorAutorDto;
import br.alura.com.livraria.service.RelatorioService;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

	@Autowired
	private RelatorioService service;
	
	@GetMapping("/livraria")
	public List<QtdLivrosPorAutorDto> relatorioDeAutores(){
		return service.relatorioDeAutores();
	}
}
