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

import br.alura.com.livraria.dto.AtualizacaoAutorFormDto;
import br.alura.com.livraria.dto.AutorDto;
import br.alura.com.livraria.dto.AutorFormDto;
import br.alura.com.livraria.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/autores")
@Api(tags="Autores")
public class AutorController {


	@Autowired
	private AutorService service;

 	
	@GetMapping
	@ApiOperation("Listar autores")
	public Page<AutorDto> listar(Pageable paginacao) {

		return service.listar(paginacao);

	}

	@PostMapping
	@ApiOperation("Cadastrar novos autores")
	public ResponseEntity<AutorDto> cadastrar(@RequestBody @Valid AutorFormDto dto, UriComponentsBuilder uriBuilder) {

		AutorDto autorDto = service.cadastrar(dto);
		URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autorDto.getId()).toUri();
		return ResponseEntity.created(uri).body(autorDto);
	}
	
	
	@PutMapping
	@ApiOperation("Atualizar dados de  autores")
	public ResponseEntity<AutorDto> atualizar(@RequestBody @Valid AtualizacaoAutorFormDto dto) {
		AutorDto autorDto = service.atualizar(dto);
	
		return ResponseEntity.ok(autorDto);
	}
	
	
	@DeleteMapping("/{id}")
	@ApiOperation("Excluir autores")
	public ResponseEntity<AutorDto> excluir(@PathVariable @NotNull Long id) {
		service.excluir(id);
	
		return ResponseEntity.noContent().build();
	}
			

	@GetMapping("/{id}")
	@ApiOperation("Detalhar autores")
	public ResponseEntity<AutorDto> detalhar(@PathVariable @NotNull Long id) {
		AutorDto dto = service.detalhar(id);
	
		return ResponseEntity.ok(dto);
	}
					
	
	
	
	
}
