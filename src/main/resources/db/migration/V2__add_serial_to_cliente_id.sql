CREATE SEQUENCE cliente_id_seq;

ALTER TABLE cliente
    ALTER COLUMN id SET DEFAULT nextval('cliente_id_seq');