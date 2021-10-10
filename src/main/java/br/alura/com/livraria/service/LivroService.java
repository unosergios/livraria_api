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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import br.alura.com.livraria.dto.LivroDto;
import br.alura.com.livraria.dto.LivroFormDto;
import br.alura.com.livraria.modelo.Livro;
import br.alura.com.livraria.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	private ModelMapper modelMapper = new ModelMapper();
	
	
	public Page<LivroDto> listar(Pageable paginacao) {
        Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(t -> modelMapper.map(t, LivroDto.class));

	}	
	
	@Transactional
	public void cadastrar(LivroFormDto dto) {

		Livro livro = modelMapper.map(dto, Livro.class);
        livro.setId(null);
		livroRepository.save(livro);
	}
	
	
	
}
