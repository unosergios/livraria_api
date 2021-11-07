package br.alura.com.livraria.service;

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
	
	public Page<UsuarioDto> listar(Pageable paginacao) {
		Page<Usuario> usuarios = usuarioRepository.findAll(paginacao);
		return usuarios.map(t -> modelMapper.map(t,UsuarioDto.class));		
		}
	

	@Transactional
	public UsuarioDto cadastrar(UsuarioFormDto dto ) {
		Usuario usuario = modelMapper.map(dto, Usuario.class);
		
		Perfil perfil = perfilRepository.getById(dto.getPerfilId());
		
		usuario.adicionarPerfil(perfil);
		
	//	String senha = new Random().nextInt(999999)+ " ";  // soma com um strin para transforma em string
	//	usuario.setSenha(senha);     		               // coloca uma ramge 999999 para gerar uma

		

		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));   
		
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
