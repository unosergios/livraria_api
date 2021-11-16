package br.alura.com.livraria.service;

import static org.junit.jupiter.api.Assertions.assertEquals;


import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import br.alura.com.livraria.dto.AutorDto;
import br.alura.com.livraria.dto.AutorFormDto;
import br.alura.com.livraria.repository.AutorRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class AutorServiceTest {

	@Mock
    private AutorRepository autorRepository;	
	
	@InjectMocks
	private AutorService service;
	
	@Test
	void deveriaCadastrarUmAutor() {
        AutorFormDto formDto = new AutorFormDto(
        	"Ana Lima",
        	"analimao@gmail.com",
        	LocalDate.now(),
        	"tttteessteee");

       AutorDto dto = service.cadastrar(formDto);
       
       assertEquals(formDto.getNome(), dto.getNome());
       assertEquals(formDto.getEmail(), dto.getEmail());
       assertEquals(formDto.getMiniCurriculum(), dto.getMiniCurriculum());
       
       Mockito
   	     .verify(autorRepository).save(Mockito.any());     
       
   		
	}

}
