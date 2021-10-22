package br.alura.com.livraria.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.alura.com.livraria.dto.AtualizacaoAutorFormDto;
import br.alura.com.livraria.dto.AutorDto;
import br.alura.com.livraria.dto.AutorFormDto;
import br.alura.com.livraria.modelo.Autor;
import br.alura.com.livraria.repository.AutorRepository;

@Service
public class AutorService {

	@Autowired
    private AutorRepository autorRepository;
	private ModelMapper modelMapper = new ModelMapper();
	

	
	public Page<AutorDto> listar(Pageable paginacao) {
        Page<Autor> autores = autorRepository.findAll(paginacao);
		
		return autores.map(t -> modelMapper.map(t, AutorDto.class));

	}

	@Transactional
	public AutorDto cadastrar(AutorFormDto dto) {
		Autor autor = modelMapper.map(dto, Autor.class);
		autorRepository.save(autor);
		return modelMapper.map(autor, AutorDto.class);
	}


	@Transactional
	public AutorDto atualizar(AtualizacaoAutorFormDto dto) {
		 Autor autor = autorRepository.getById(dto.getId());
		 autor.atualizarInformacoesDoAutor(dto.getNome(),dto.getEmail(),dto.getDataNascimento(),
				   dto.getMiniCurriculum());
		 return modelMapper.map(autor, AutorDto.class);
	}

	@Transactional
	public void excluir(Long id) {
		autorRepository.deleteById(id);
		
	}

	
	public AutorDto detalhar(Long id) {
		 Autor autor = autorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());;

		 return modelMapper.map(autor, AutorDto.class);
	}	
	
}
