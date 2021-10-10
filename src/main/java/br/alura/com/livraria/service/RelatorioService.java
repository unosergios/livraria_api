package br.alura.com.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.alura.com.livraria.dto.QtdLivrosPorAutorDto;
import br.alura.com.livraria.repository.AutorRepository;
import br.alura.com.livraria.repository.LivroRepository;

@Service
public class RelatorioService {

    @Autowired
	private AutorRepository repository;
    
	
	public List<QtdLivrosPorAutorDto> relatorioDeAutores() {
			return repository.relatorioDeAutores();
	}
	

}
