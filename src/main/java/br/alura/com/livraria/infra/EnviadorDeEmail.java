package br.alura.com.livraria.infra;

import org.springframework.scheduling.annotation.Async;

public interface EnviadorDeEmail {

	void enviarEmail(String destinatario, String assunto, String mensagem);

}