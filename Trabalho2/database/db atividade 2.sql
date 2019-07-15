CREATE DATABASE DB_ltpw;
USE DB_ltpw;

CREATE TABLE IF NOT EXISTS TB_cursos (
	cod	INT ,
    nome VARCHAR(128),
    categoria VARCHAR(64),
    carga	VARCHAR(16),
    preco	VARCHAR(16),
    PRIMARY KEY(cod)
);

SELECT * FROM TB_cursos;
select * from TB_cursos where cod=111;
DELETE FROM TB_cursos WHERE cod=111;
DROP TABLE TB_cursos;