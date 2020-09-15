create schema org;

create table org.card
(
    id           bigint not null
        constraint card_pkey
            primary key,
    active       boolean,
    component    varchar,
    created_by   varchar,
    created_date timestamp,
    description  varchar,
    expire_date  timestamp,
    group_name   varchar,
    original_url varchar,
    short_url    varchar,
    team         varchar,
    title        varchar,
    tribe        varchar,
    updated_by   varchar,
    updated_date timestamp
);

create table org.group
(
    id           bigint not null
        constraint group_pkey
            primary key,
    created_by   varchar,
    created_date timestamp,
    description  varchar,
    name         varchar,
    updated_by   varchar,
    updated_date timestamp,
    component    varchar,
    team         varchar,
    tribe        varchar,
    active       boolean,
    admin        varchar
);

create table org.user
(
    id     integer not null
        constraint user_pkey
            primary key,
    email  varchar,
    active boolean not null
);

create table org.icon
(
	id serial not null,
	name varchar,
	type varchar,
	card_id int,
	file oid
);


create unique index icon_id_uindex
	on org.icon (id);

alter table org.icon
	add constraint icon_pk
		primary key (id);

-----------------------------------------------

create table org."user-in-group"
(
	id serial not null,
	email varchar,
	group_name varchar,
	added_date date,
	added_by varchar
);

create unique index "user-in-group_id_uindex"
	on org."user-in-group" (id);

alter table org."user-in-group"
	add constraint "user-in-group_pk"
		primary key (id);

--------------------------------------------

create table org.url
(
	id serial not null,
	long_url varchar,
	created_date date,
	card_id int,
	expires_date date
);

create unique index url_id_uindex
	on org.url (id);

alter table org.url
	add constraint url_pk
		primary key (id);

--------------------------------------

create table org."card-in-group"
(
	id serial not null,
	card_id int,
	group_id int,
	added_date date,
	added_by varchar
);

create unique index "card-in-group_id_uindex"
	on org."card-in-group" (id);

alter table org."card-in-group"
	add constraint "card-in-group_pk"
		primary key (id);
