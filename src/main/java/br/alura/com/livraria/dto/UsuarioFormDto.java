package br.alura.com.livraria.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

// livraria
@Getter
@Setter
public class UsuarioFormDto {

    @NotBlank                        
	private String nome;
    
    @NotBlank
	private String login;	
    
   //@NotBlank
   //@Size(min=8 , max=16)
   //private String senha;
    
    @NotNull
    private Long perfilId;
    
    @NotBlank
    @Email
    private String email;
	
}
