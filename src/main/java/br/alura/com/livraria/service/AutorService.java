package br.alura.com.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.alura.com.livraria.dto.AutorDto;
import br.alura.com.livraria.dto.AutorFormDto;
import br.alura.com.livraria.modelo.Autor;

@Service
public class AutorService {


	private ModelMapper modelMapper = new ModelMapper();
	private List<Autor> autores = new ArrayList<>();

	
	public List<AutorDto> listar() {

		return autores.stream().map(t -> modelMapper.map(t, AutorDto.class)).collect(Collectors.toList());

	}

	
	public void cadastrar(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		autores.add(autor);
	}	
	
}
