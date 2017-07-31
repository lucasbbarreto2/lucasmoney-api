CREATE TABLE pessoa(
	id serial PRIMARY KEY,
	nome VARCHAR NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR,
	numero VARCHAR,
	complemento VARCHAR,
	bairro VARCHAR,
	cep VARCHAR,
	cidade VARCHAR,
	estado VARCHAR
);

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, 
					bairro, cep, cidade, estado)
VALUES
	('Lucas Barreto', true, 'Rua Santa Rita', '1281', null, 
	'Bairro das Laranjeiras', '29175579', 'Serra', 'ES'),
	('Lydia Karla', true, 'Rua Silvino Grecco', '220', 'apt. 202', 
	'Jardim Camburi', null, 'Vitória', 'ES'),
	('Caroliny Montay', true, 'Rua Casuarina', null, null,
	'Balneário de Carapebus', null, 'Serra', 'ES'),
	('Monica Barbosa', false, 'Praia de Itaipava', null, null,
	'Itaipava', null, 'Itapemirim', 'ES'),
	('Sebastião de Vasconcelos', true, 'Rua Firmino Fragoso', '231', 'apt. 201',
	'Madureira', '21351090', 'Rio de Janeiro', 'RJ')
;
	