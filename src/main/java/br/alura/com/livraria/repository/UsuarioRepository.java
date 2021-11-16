package br.alura.com.livraria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.alura.com.livraria.modelo.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);

	@Query("SELECT u FROM Usuario u JOIN FETCH u.perfis WHERE u.id=:id")
	Optional<Usuario> carregarPorIdComPerfis(Long id);


	
}