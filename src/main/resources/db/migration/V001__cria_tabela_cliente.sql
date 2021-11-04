CREATE TABLE IF NOT EXISTS cliente
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    nome     VARCHAR(60)  NOT NULL,
    email    VARCHAR(255) NOT NULL,
    telefone VARCHAR(20)  NOT NULL,

    CONSTRAINT PK_cliente PRIMARY KEY (id)
)