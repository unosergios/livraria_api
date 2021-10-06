package br.alura.com.livraria.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.alura.com.livraria.dto.AutorDto;
import br.alura.com.livraria.dto.AutorFormDto;
import br.alura.com.livraria.modelo.Autor;
import br.alura.com.livraria.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
    private AutorRepository autorRepository;
	private ModelMapper modelMapper = new ModelMapper();
	private List<Autor> autores = new ArrayList<>();

	
	public Page<AutorDto> listar(Pageable paginacao) {
        Page<Autor> autores = autorRepository.findAll(paginacao);
		
		return autores.map(t -> modelMapper.map(t, AutorDto.class));

	}

	
	public void cadastrar(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		autorRepository.save(autor);
	}	
	
}
