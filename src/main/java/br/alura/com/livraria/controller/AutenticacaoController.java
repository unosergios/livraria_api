package br.alura.com.livraria.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.alura.com.livraria.dto.LoginFormDto;
import br.alura.com.livraria.dto.TokenDto;
import br.alura.com.livraria.infra.security.AutenticacaoService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AutenticacaoService service;

	@PostMapping
	public TokenDto autenticar(@RequestBody @Valid LoginFormDto dto ) {
	
		return new TokenDto(service.autenticar(dto));

	}
}
