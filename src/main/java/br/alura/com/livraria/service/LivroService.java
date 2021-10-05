package br.alura.com.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.alura.com.livraria.dto.LivroDto;
import br.alura.com.livraria.dto.LivroFormDto;
import br.alura.com.livraria.modelo.Livro;

@Service
public class LivroService {

	private ModelMapper modelMapper = new ModelMapper();
	private List<Livro> livros = new ArrayList<>();	
	
	
	public List<LivroDto> listar() {

		return livros.stream().map(t -> modelMapper.map(t, LivroDto.class)).collect(Collectors.toList());

	}	
	
	public void cadastrar(LivroFormDto dto) {

		Livro livro = modelMapper.map(dto, Livro.class);

		livros.add(livro);
	}
	
	
	
}
