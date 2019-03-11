create table if not exists users
(
	id serial not null
		constraint users_pk
			primary key,
	name varchar(128),
	login varchar(100),
	password varchar(20),
	occupation varchar(90),
	photo varchar(1000)
);

alter table users owner to postgres;

create unique index if not exists users_id_uindex
	on users (id);

create unique index if not exists users_login_uindex
	on users (login);

	create table if not exists messages
(
	id serial not null
		constraint messages_pk
			primary key,
	id1 integer
		constraint messages_users_id_fk
			references users
				on delete cascade,
	id2 integer
		constraint messages_users_id_fk_2
			references users
				on delete cascade,
	messages varchar(21844),
	time timestamp default now()
);

alter table messages owner to postgres;

create table if not exists liked
(
	id serial not null
		constraint liked_pk
			primary key,
	id1 integer
		constraint liked_users_id_fk
			references users,
	id2 integer
		constraint liked_users_id_fk_2
			references users,
	sumpathy boolean
);

alter table liked owner to postgres;

create unique index if not exists liked_id_uindex
	on liked (id);