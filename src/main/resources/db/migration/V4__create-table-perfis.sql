CREATE TABLE perfis (
  id bigint NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  PRIMARY KEY (id)
  );

  CREATE TABLE perfis_usuarios (
   usuario_id bigint not null ,
   perfil_id bigint not null ,
  PRIMARY KEY (usuario_id, perfil_id),
  foreign key(usuario_id) references usuarios(id),
  foreign key(perfil_id) references perfis(id)
  );
  
  INSERT into perfis (nome) values('ROLE_ADMIN');
  INSERT into perfis (nome) values('ROLE_COMUM');
  INSERT into perfis (nome) values('ROLE_GERENTE');
 
