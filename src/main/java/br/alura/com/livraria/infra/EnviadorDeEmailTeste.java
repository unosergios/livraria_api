package br.alura.com.livraria.infra;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default","test"})
public class EnviadorDeEmailTeste implements EnviadorDeEmail {
	
public void enviarEmail(String destinatario,String assunto,String mensagem) {
	
	System.out.println(" Enviando Email : ");
	System.out.println(" Destinatário : " + destinatario);
	System.out.println(" Assunto      : " + assunto);
	System.out.println(" Mensagem     : " + mensagem);	
	
}




}
