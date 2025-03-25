-- DADOS CIDADE --
INSERT INTO cidade (cid_nome, cid_uf) VALUES ('São Paulo', 'SP') ON CONFLICT (cid_nome) DO NOTHING;
INSERT INTO cidade (cid_nome, cid_uf) VALUES ('Rio de Janeiro', 'RJ') ON CONFLICT (cid_nome) DO NOTHING;
INSERT INTO cidade (cid_nome, cid_uf) VALUES ('Belo Horizonte', 'MG') ON CONFLICT (cid_nome) DO NOTHING;

-- DADOS ENDEREÇO --
INSERT INTO endereco (end_tipo_logradouro, end_logradouro, end_numero, end_bairro, cid_id)
VALUES
('Rua', 'Av. Paulista', 1000, 'Bela Vista', (SELECT cid_id FROM cidade WHERE cid_nome = 'São Paulo' LIMIT 1)),
('Avenida', 'Copacabana', 500, 'Copacabana', (SELECT cid_id FROM cidade WHERE cid_nome = 'Rio de Janeiro' LIMIT 1)),
('Rua', 'Savassi', 300, 'Savassi', (SELECT cid_id FROM cidade WHERE cid_nome = 'Belo Horizonte' LIMIT 1))
ON CONFLICT DO NOTHING;

-- DADOS PESSOA --

-- Inserindo pessoas
INSERT INTO pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES
('João Silva', '1990-05-20', 'Masculino', 'Maria Silva', 'Carlos Silva') ON CONFLICT DO NOTHING;

INSERT INTO pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES
('Ana Souza', '1985-10-15', 'Feminino', 'Laura Souza', 'Pedro Souza') ON CONFLICT DO NOTHING;

INSERT INTO pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai) VALUES
('Carlos Oliveira', '1978-12-05', 'Masculino', 'Beatriz Oliveira', 'José Oliveira') ON CONFLICT DO NOTHING;

-- Inserindo relacionamento entre pessoas e endereços na tabela de associação pessoa_endereco
INSERT INTO pessoa_endereco (pes_id, end_id)
VALUES
((SELECT pes_id FROM pessoa WHERE pes_nome = 'João Silva' LIMIT 1),
 (SELECT end_id FROM endereco WHERE end_logradouro = 'Av. Paulista' LIMIT 1)),

((SELECT pes_id FROM pessoa WHERE pes_nome = 'Ana Souza' LIMIT 1),
 (SELECT end_id FROM endereco WHERE end_logradouro = 'Copacabana' LIMIT 1)),

((SELECT pes_id FROM pessoa WHERE pes_nome = 'Carlos Oliveira' LIMIT 1),
 (SELECT end_id FROM endereco WHERE end_logradouro = 'Savassi' LIMIT 1))
ON CONFLICT DO NOTHING;
