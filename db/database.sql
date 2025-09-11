USE sistemavendas;

#Código do produto, nome, descrição, preço de venda e quantidade em estoque
CREATE TABLE Produto (
	id int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    descricao VARCHAR(255) NULL,
    precoVenda DECIMAL(10, 2) NOT NULL,
    quantidade int NOT NULL
);

#Código do cliente, nome, endereço, e-mail e telefone
CREATE TABLE Cliente (
	id int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL
);

USE sistemavendas;

#Código da nota, cliente (selecionado a partir de uma lista de clientes cadastrados), produto (selecionado a partir de uma lista de produtos cadastrados), quantidade vendida e data da venda.
CREATE TABLE Nota (
	id int PRIMARY KEY AUTO_INCREMENT,
    dataVenda datetime,
    FK_Cliente int,
    
    FOREIGN KEY (FK_Cliente) REFERENCES Cliente(id)
);

USE sistemavendas;

CREATE TABLE ItensNota (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantidade INT NOT NULL,
    FK_Nota INT NOT NULL,
    FK_Produtos INT NOT NULL,
    FOREIGN KEY (FK_Nota) REFERENCES Nota(id),
    FOREIGN KEY (FK_Produtos) REFERENCES Produto(id)
);