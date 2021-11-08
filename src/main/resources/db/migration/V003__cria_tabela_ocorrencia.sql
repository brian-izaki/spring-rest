CREATE TABLE IF NOT EXISTS ocorrencia
(
    id            BIGINT   NOT NULL AUTO_INCREMENT,
    entrega_id    BIGINT   NOT NULL,
    descricao     TEXT     NOT NULL,
    data_registro DATETIME NOT NULL,

    CONSTRAINT PK_ocorrencia PRIMARY KEY (id),
    CONSTRAINT FK_ocorrencia_entrega FOREIGN KEY (entrega_id) REFERENCES entrega (id)
)