CREATE TABLE autores (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  data_nascimento date NOT NULL,  
  email varchar(60) NOT NULL,
  mini_curriculum varchar(600) NULL,
  PRIMARY KEY (id)
  );