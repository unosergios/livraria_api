package br.alura.com.livraria.service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.alura.com.livraria.dto.AtualizacaoLivroFormDto;
import br.alura.com.livraria.dto.LivroDto;
import br.alura.com.livraria.dto.LivroFormDto;
import br.alura.com.livraria.modelo.Autor;
import br.alura.com.livraria.modelo.Livro;
import br.alura.com.livraria.repository.AutorRepository;
import br.alura.com.livraria.repository.LivroRepository;

@Service
public class LivroService {

	@Autowired
	private LivroRepository livroRepository;
	
    @Autowired
	private AutorRepository autorRepository;  
	
	private ModelMapper modelMapper = new ModelMapper();
	
	
	public Page<LivroDto> listar(Pageable paginacao) {
        Page<Livro> livros = livroRepository.findAll(paginacao);
		return livros.map(t -> modelMapper.map(t, LivroDto.class));

	}	
	
	@Transactional
	public LivroDto cadastrar(LivroFormDto dto) {

		Long autorId = dto.getAutorId();     
		
		try {
		    Autor autor = autorRepository.getById(autorId);  
			Livro livro = modelMapper.map(dto, Livro.class);
			
			livro.setAutor(autor);
	        livro.setId(null);
			livroRepository.save(livro);
		 	return modelMapper.map(livro, LivroDto.class);		    
		
		} catch (EntityNotFoundException e){
			throw new IllegalArgumentException("Autor Inexistente");
		}
	}
	
	
	@Transactional
    public LivroDto atualizar(AtualizacaoLivroFormDto dto) {
		  Livro livro = livroRepository.getById(dto.getId());
		
		  livro.atualizarInformacoesDoLivro(dto.getTitulo(),dto.getDataDeLancamento(),dto.getNumeroDePaginas());
		  return modelMapper.map(livro, LivroDto.class);			  
	}

	@Transactional
	public void excluir(Long id) {
		
		livroRepository.deleteById(id);
		
	}

	public LivroDto detalhar(Long id) {
		Livro livro = livroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
		return modelMapper.map(livro, LivroDto.class);
	}
	
	
	

}
