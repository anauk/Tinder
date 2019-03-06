create table if not exists ag_tinder_users
(
	id int not null,
	name varchar(128) null,
	occupation varchar(256) null,
	login varchar(128) null,
	password varchar(32) null,
	photo varchar(1024) null,
	constraint `ag-tinder_users_id_uindex`
		unique (id),
	constraint `ag-tinder_users_login_uindex`
		unique (login)
)
;

alter table ag_tinder_users
	add primary key (id)
;

create table if not exists ag_tinder_liked
(
	id int auto_increment,
	user1_id int null,
	user2_id int null,
	sympathy tinyint(1) null,
	constraint `ag-tinder_liked_id_uindex`
		unique (id),
	constraint `ag-tinder_liked_ag-tinder_users_id_fk`
		foreign key (user1_id) references ag_tinder_users (id)
			on delete cascade,
	constraint `ag-tinder_liked_ag-tinder_users_id_fk_2`
		foreign key (user2_id) references ag_tinder_users (id)
			on delete cascade
)
;

alter table ag_tinder_liked
	add primary key (id)
;

create table if not exists ag_tinder_messages
(
	id int auto_increment,
	user1_id int null,
	user2_id int null,
	message varchar(21844) null,
	time timestamp default CURRENT_TIMESTAMP null,
	constraint `ag-tinder_messages_id_uindex`
		unique (id),
	constraint `ag-tinder_messages_ag-tinder_users_id_fk`
		foreign key (user1_id) references ag_tinder_users (id)
			on delete cascade,
	constraint `ag-tinder_messages_ag-tinder_users_id_fk_2`
		foreign key (user2_id) references ag_tinder_users (id)
			on delete cascade
)
;

alter table ag_tinder_messages
	add primary key (id)
;
