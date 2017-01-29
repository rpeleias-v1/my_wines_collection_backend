INSERT INTO Usuario (id, nome, senha, email, login, data_criacao) VALUES (1, 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'rpeleias@hotmail.com', 'user', PARSEDATETIME('01-01-2017', 'dd-MM-yyyy'));
INSERT INTO Usuario (id, nome, senha, email, login, data_criacao) VALUES (2, 'admin2', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'rpeleias2@hotmail.com', 'admin', PARSEDATETIME('01-01-2017', 'dd-MM-yyyy'));
INSERT INTO Usuario (id, nome, senha, email, login, data_criacao) VALUES (3, 'admin3', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'rpeleias3@hotmail.com', 'user2', PARSEDATETIME('01-01-2017', 'dd-MM-yyyy'));

INSERT INTO permissao (id, tipo) VALUES (1, 'ROLE_USER');
INSERT INTO permissao (id, tipo) VALUES (2, 'ROLE_ADMIN');

INSERT INTO USUARIO_PERMISSOES(USUARIOS_ID, PERMISSOES_ID) VALUES(1,1);
