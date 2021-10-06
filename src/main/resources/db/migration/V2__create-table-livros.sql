CREATE TABLE livros (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  titulo varchar(100) NOT NULL,
  numero_de_paginas int(11) NOT NULL,
  data_de_lancamento date NOT NULL,  
  autor_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (autor_id) references autores(id)
  ) 