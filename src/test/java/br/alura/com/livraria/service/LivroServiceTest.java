package br.alura.com.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.alura.com.livraria.dto.LivroDto;
import br.alura.com.livraria.dto.LivroFormDto;
import br.alura.com.livraria.repository.AutorRepository;
import br.alura.com.livraria.repository.LivroRepository;

@ExtendWith(MockitoExtension.class)
class LivroServiceTest {

	@Mock
	private LivroRepository livroRepository;
	
    @Mock
	private AutorRepository autorRepository;  	
	
    @InjectMocks
	private LivroService service;

	@Test
	void deveriaCadastrarUmLivro() {
		
		LivroFormDto formDto = new LivroFormDto(
		  "Spring Boot",
		  LocalDate.now(),
		  120,
		  10l);
		

    	LivroDto dto = service.cadastrar(formDto);
    	
    	Mockito
    	.verify(livroRepository.save(Mockito.any()));
    	
    	assertEquals(formDto.getTitulo(), dto.getTitulo());
    	assertEquals(formDto.getNumeroDePaginas(), dto.getNumeroDePaginas());
       	
	}

	
	@Test
	void naoDeveriaCadastrarUmLivroComUmAutorInexistente() {
		
		LivroFormDto formDto = new LivroFormDto(
		  "Spring Boot",
		  LocalDate.now(),
		  120,
		  10l);
		

		Mockito
		.when(autorRepository.getById(formDto.getAutorId()))
		.thenThrow(EntityNotFoundException.class);
		
    	assertThrows(IllegalArgumentException.class,() -> service.cadastrar(formDto));
    	
	
	}
	
}
