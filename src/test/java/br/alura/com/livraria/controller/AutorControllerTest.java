package br.alura.com.livraria.controller;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import br.alura.com.livraria.infra.security.TokenService;
import br.alura.com.livraria.modelo.Perfil;
import br.alura.com.livraria.modelo.Usuario;
import br.alura.com.livraria.repository.PerfilRepository;
import br.alura.com.livraria.repository.UsuarioRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class AutorControllerTest  {

	@Autowired
    private MockMvc mvc;	
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private String token;
	
	@BeforeEach
	public void gerarToken() {
		
		Usuario logado = new Usuario("Sergio", "unox", "123456");
		Perfil admin = perfilRepository.findById(3l).get();
		
		
		logado.adicionarPerfil(admin);
		
		
		usuarioRepository.save(logado);
		

		
		Authentication authentication = new UsernamePasswordAuthenticationToken(logado, logado.getLogin());
		this.token = tokenService.gerarToken(authentication);
		
	
	}
	
	
	@Test
	void naoDeveriaCadastrarAutorComDadosIncompletos() throws Exception {
		String json = "{}";
		 mvc
		   .perform(post("/autores")
		   .contentType(MediaType.APPLICATION_JSON)
		   .content(json)
		   .header("Authorization", "Bearer  " + token))
		   .andExpect(status().isBadRequest());
	}

	@Test
	void deveriaCadastrarAutorComDadosCompletos() throws Exception {
		String json = "{\"nome\"  : \"Ana Lima\","
				    + "\"email\" : \"analima@gmail.com\","
				    + "\"dataNascimento\" : \"01/06/1975\","
				    + "\"miniCurriculum\" : \"teste 4\" } ";		
	
		 mvc
		   .perform(post("/autores")
		   .contentType(MediaType.APPLICATION_JSON)
		   .content(json)
		   .header("Authorization", "Bearer  " + token))
		   .andExpect(status().isCreated())
		   .andExpect(header().exists("Location"))
		   .andExpect(content().json(json))
		   ;
	}	
}
