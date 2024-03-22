create table parcelamento (
	id serial not null,
	cliente_id bigint not null,
	descricao varchar(20) not null,
	valor_total decimal(10, 2) not null,
	quantidade_parcelas smallserial,
	data_criacao date not null,
	primary key (id),
	foreign key (cliente_id) references cliente (id)
);
