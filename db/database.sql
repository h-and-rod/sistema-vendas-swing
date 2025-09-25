CREATE DATABASE SistemaVendas;

USE sistemavendas;

-- Código do produto, nome, descrição, preço de venda e quantidade em estoque
CREATE TABLE Produto (
	id int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    descricao VARCHAR(255) NULL,
    precoVenda DECIMAL(10, 2) NOT NULL,
    quantidade int NOT NULL
);

-- Código do cliente, nome, endereço, e-mail e telefone
CREATE TABLE Cliente (
	id int PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    endereco VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    telefone VARCHAR(15) NOT NULL
);

USE sistemavendas;

-- Código da nota, cliente (selecionado a partir de uma lista de clientes cadastrados), produto (selecionado a partir de uma lista de produtos cadastrados), quantidade vendida e data da venda.
CREATE TABLE Nota (
	id int PRIMARY KEY AUTO_INCREMENT,
    dataVenda datetime,
    FK_Cliente int,
    valorTotal DECIMAL(10,2) NULL,
    
    FOREIGN KEY (FK_Cliente) REFERENCES Cliente(id)
);

USE sistemavendas;

CREATE TABLE ItensNota (
    id INT PRIMARY KEY AUTO_INCREMENT,
    quantidade INT NOT NULL,
    FK_Nota INT NOT NULL,
    FK_Produtos INT NOT NULL,
    valorUnitario DECIMAL(10,2) NOT NULL,
    valorTotal DECIMAL(10,2) GENERATED ALWAYS AS (valorUnitario * quantidade) STORED,
    FOREIGN KEY (FK_Nota) REFERENCES Nota(id),
    FOREIGN KEY (FK_Produtos) REFERENCES Produto(id)
);

-- Criação de inserts para popular as tabelas com dados fictícios
USE sistemavendas;

INSERT INTO Produto (nome, descricao, precoVenda, quantidade)
VALUES 
('Notebook Dell', 'Notebook i5 8GB RAM SSD 256GB', 3500.00, 10),
('Mouse Gamer', 'Mouse óptico RGB com 6 botões', 120.00, 50),
('Teclado Mecânico', 'Teclado mecânico ABNT2 com LED', 250.00, 30);

INSERT INTO Cliente (nome, endereco, email, telefone)
VALUES 
('João Silva', 'Rua A, 123', 'joao@email.com', '11999990001'),
('Maria Oliveira', 'Av. B, 456', 'maria@email.com', '11999990002'),
('Carlos Souza', 'Praça C, 789', 'carlos@email.com', '11999990003');

INSERT INTO Nota (dataVenda, FK_Cliente, valorTotal)
VALUES
('2025-09-18 10:00:00', 1, NULL),
('2025-09-18 11:00:00', 2, NULL),
('2025-09-18 12:00:00', 3, NULL);

-- Itens da Nota 1 (Cliente João)
INSERT INTO ItensNota (quantidade, FK_Nota, FK_Produtos, valorUnitario)
VALUES
(1, 1, 1, 3500.00), 
(2, 1, 2, 120.00);

-- Itens da Nota 2 (Cliente Maria)
INSERT INTO ItensNota (quantidade, FK_Nota, FK_Produtos, valorUnitario)
VALUES
(1, 2, 3, 250.00); 

-- Itens da Nota 3 (Cliente Carlos)
INSERT INTO ItensNota (quantidade, FK_Nota, FK_Produtos, valorUnitario)
VALUES
(2, 3, 2, 120.00),
(1, 3, 1, 3500.00);
