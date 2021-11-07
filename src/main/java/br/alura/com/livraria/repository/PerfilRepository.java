package br.alura.com.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.alura.com.livraria.modelo.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {

	
}
