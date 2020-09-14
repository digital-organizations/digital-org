-- auto-generated definition
create table digital.card
(
    id  serial not null constraint card_pk primary key,
    title             varchar,
    description       varchar,
    original_url      varchar,
    short_url         varchar,
    expire_date       date,
    created_date      date,
    created_by        varchar,
    tribe             varchar,
    team              varchar,
    component         varchar,
    updated_date      date,
    updated_by        varchar,
    group_name        varchar,
    active            boolean,
    file              oid

);
-----------------------------------------------

create table digital.icon
(
	id serial not null,
	name varchar,
	type varchar,
	card_id int,
	file bytea
);

create unique index icon_id_uindex
	on digital.icon (id);

alter table digital.icon
	add constraint icon_pk
		primary key (id);

-----------------------------------------------

create table digital."user-in-group"
(
	id serial not null,
	email varchar,
	group_name varchar,
	added_date date,
	added_by varchar
);

create unique index "user-in-group_id_uindex"
	on digital."user-in-group" (id);

alter table digital."user-in-group"
	add constraint "user-in-group_pk"
		primary key (id);

--------------------------------------------

create table digital.url
(
	id serial not null,
	long_url varchar,
	created_date date,
	expires_date date
);

create unique index url_id_uindex
	on digital.url (id);

alter table digital.url
	add constraint url_pk
		primary key (id);

--------------------------------------

create table digital."card-in-group"
(
	id serial not null,
	card_id int,
	group_id int,
	added_date date,
	added_by varchar
);

create unique index "card-in-group_id_uindex"
	on digital."card-in-group" (id);

alter table digital."card-in-group"
	add constraint "card-in-group_pk"
		primary key (id);

