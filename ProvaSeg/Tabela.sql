create database ProvaSeg;
use ProvaSeg;

CREATE TABLE paciente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL
);

-- Criação tabela medico
CREATE TABLE medico (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) UNIQUE NOT NULL,
    especialidade VARCHAR(100) NOT NULL
);

-- Criação da tabela acido_urico (Será criptografada o campo resultado pela chave secreta)
CREATE TABLE exame (
    id INT AUTO_INCREMENT PRIMARY KEY,
    resultado VARCHAR(255) NOT NULL, 
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id),
    FOREIGN KEY (id_medico) REFERENCES medico(id)

);

-- Criação tabela valorespadroes
CREATE TABLE valorespadroes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    limite_inferior DECIMAL(10, 2),
    limite_superior DECIMAL(10, 2),
    unidade VARCHAR(50) NOT NULL,
    valor_referencia VARCHAR(100)
);

-- Criação da tabela senha (Será criptografada pela senha do usuário)
CREATE TABLE senha (
    id INT AUTO_INCREMENT PRIMARY KEY,
    chave_secreta VARCHAR(100) NOT NULL
);

-- Criação da tabela usuário (Irá armazenar o hash da senha)
CREATE TABLE usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    senha VARCHAR(64) NOT NULL
);


INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. Mario de Andrade', '123.451.985-64', 'Hematologista');

INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dra. Pauline de Oliveira', '122.555.777.98', 'Cardiologista');

INSERT INTO medico (nome, cpf, especialidade) VALUES
('Dr. Robson Robson', '123.445.999-00', 'Dermatologia');


INSERT INTO paciente (nome, cpf) VALUES
('Shrek da Costa', '129.200.111.01');

INSERT INTO paciente (nome, cpf) VALUES
('Darwin Raglan Caspian Ahab Poseidon Nicodemius Watterson III', '987.654.321-00');

INSERT INTO paciente (nome, cpf) VALUES
('Samus Aran', '123.444.666.14');

INSERT INTO valorespadroes(limite_inferior, limite_superior, unidade, valor_referencia) VALUES
(135.0, 145.0, 'mEq/L', 'Valores normais'); 
