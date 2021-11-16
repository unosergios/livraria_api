package br.alura.com.livraria.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.alura.com.livraria.repository.UsuarioRepository;

@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;	
	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	// so para ver usuario e senha - autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(bCryptPasswordEncoder);

	}

    // configuracao  de autorizacao - controle de acesso

	@Override
	protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()              // - para configurar como vao ser as requisiçoes
          .antMatchers(HttpMethod.POST,"/auth").permitAll()
          .antMatchers("/usuarios/**").hasRole("ADMIN")
          
          .antMatchers(HttpMethod.GET,"/livros/**").hasRole("COMUM")
          .antMatchers(HttpMethod.POST,"/livros").hasRole("GERENTE")
          .antMatchers(HttpMethod.DELETE,"/livros/**").hasRole("GERENTE")
          .antMatchers(HttpMethod.PUT,"/livros").hasRole("GERENTE")

          .antMatchers(HttpMethod.GET,"/autores/**").hasRole("COMUM")
          .antMatchers(HttpMethod.POST,"/autores").hasRole("GERENTE")
          .antMatchers(HttpMethod.DELETE,"/autores/**").hasRole("GERENTE")
          .antMatchers(HttpMethod.PUT,"/autores").hasRole("GERENTE")
          
          .anyRequest().authenticated()      // - tem que estar logado
          .and() 

          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and().csrf().disable()               // - desabilitar a proteçao automatica do csrf
          .addFilterBefore(new VerificacaoTokenFilter(tokenService,usuarioRepository),
        		  UsernamePasswordAuthenticationFilter.class);

	}
	
	//  liberar o swagger
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        .antMatchers("/v2/api-docs","/configuration/ui","/swagger-resources/**","/configuration/secutiry","/swagger-resources/**","/configuration/security","/swagger-ui.html","/webjars/**");
	
	}
	

}
