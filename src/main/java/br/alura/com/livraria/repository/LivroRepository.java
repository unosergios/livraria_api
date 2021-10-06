package br.alura.com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alura.com.livraria.modelo.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

}
