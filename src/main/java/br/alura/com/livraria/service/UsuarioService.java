package br.alura.com.livraria.service;

import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.alura.com.livraria.dto.UsuarioDto;
import br.alura.com.livraria.dto.UsuarioFormDto;
import br.alura.com.livraria.infra.EnviadorDeEmail;
import br.alura.com.livraria.modelo.Perfil;
import br.alura.com.livraria.modelo.Usuario;
import br.alura.com.livraria.repository.PerfilRepository;
import br.alura.com.livraria.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private EnviadorDeEmail enviadorDeEmail;
	
	public Page<UsuarioDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t,UsuarioDto.class));		
		}
	

	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto ) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		
		usuario.adicionarPerfil(perfil);
		
		String senha = new Random().nextInt(999999)+ " ";  // soma com um strin para transforma em string
	//	usuario.setSenha(senha);     		               // coloca uma ramge 999999 para gerar uma

		String destinatario = usuario.getEmail();
		String assunto = "Bem vindo novo usuario da Livraria";
		String mensagem = String.format("Olá %s !\n\n"
				+ "Segue seus dados de acesso ao sistema Livraria :"
				+ " \n Login : %s\n Senha : %s",
				usuario.getNome(),usuario.getLogin(),senha);
		
		enviadorDeEmail.enviarEmail(destinatario, assunto, mensagem);

		usuario.setSenha(bCryptPasswordEncoder.encode(senha));   
		
		usuario.setId(null);


        usuarioRepository.save(usuario);
        
        
        return modelMapper.map(usuario, UsuarioDto.class);
	}


	public void excluir(Long id) {
		 usuarioRepository.deleteById(id);
		
	}


	public UsuarioDto detalhar(Long id) {
		 Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
         return modelMapper.map(usuario, UsuarioDto.class);


	}		
	

}
