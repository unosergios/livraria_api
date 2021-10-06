package br.alura.com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alura.com.livraria.modelo.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{

}
