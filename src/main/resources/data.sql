INSERT INTO tb_estado(nome) VALUES ('Distrito Federal');
INSERT INTO tb_estado(nome) VALUES ('Goiás');

INSERT INTO tb_perfil(nome) VALUES ('Usuário comum');
INSERT INTO tb_perfil(nome) VALUES ('Usuário administrador');

INSERT INTO tb_pessoa(nome, data, estado_id) VALUES ('João', '2023-02-12', 1);
INSERT INTO tb_pessoa(nome, data, estado_id) VALUES ('Maria', '2023-02-12', 2);

INSERT INTO tb_pessoa_perfil(pessoa_id, perfil_id) VALUES (2, 2);
INSERT INTO tb_pessoa_perfil(pessoa_id, perfil_id) VALUES (1, 1);
INSERT INTO tb_pessoa_perfil(pessoa_id, perfil_id) VALUES (1, 2);