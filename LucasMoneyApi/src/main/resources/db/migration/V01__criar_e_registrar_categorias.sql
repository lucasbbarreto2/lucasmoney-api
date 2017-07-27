CREATE TABLE categoria(
	id serial PRIMARY KEY,
	nome VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO categoria (nome) 
VALUES 
	('Lazer'),
	('Alimentação'),
	('Supermercado'),
	('Farmácia'),
	('Outros')
;
