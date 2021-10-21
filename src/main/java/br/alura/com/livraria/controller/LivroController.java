package br.alura.com.livraria.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.alura.com.livraria.dto.AtualizacaoLivroFormDto;

import br.alura.com.livraria.dto.LivroDto;
import br.alura.com.livraria.dto.LivroFormDto;

import br.alura.com.livraria.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroService service;

	@GetMapping
	public Page<LivroDto> listar(Pageable paginacao) {

		return service.listar(paginacao);

	}

	@PostMapping
	public ResponseEntity<LivroDto> cadastrar(@RequestBody @Valid LivroFormDto dto, UriComponentsBuilder uriBuilder) {

		LivroDto livroDto = service.cadastrar(dto);
		URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(livroDto.getId()).toUri();
		return ResponseEntity.created(uri).body(livroDto);
	}
	
	
	@PutMapping
	public ResponseEntity<LivroDto> atualizar(@RequestBody @Valid AtualizacaoLivroFormDto dto) {
		LivroDto livroDto = service.atualizar(dto);
	
		return ResponseEntity.ok(livroDto);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LivroDto> excluir(@PathVariable @NotNull Long id) {
		service.excluir(id);
	
		return ResponseEntity.noContent().build();
	}
			

	@GetMapping("/{id}")
	public ResponseEntity<LivroDto> detalhar(@PathVariable @NotNull Long id) {
		LivroDto dto = service.detalhar(id);
	
		return ResponseEntity.ok(dto);
	}
				
	
}
