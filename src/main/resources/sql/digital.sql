drop schema if exists digital cascade;

create schema digital;

create table digital.card
(
    id           SERIAL PRIMARY KEY,
    title        varchar,
    description  varchar,
    tribe        varchar,
    team         varchar,
    component    varchar,
    created_by   varchar,
    updated_by   varchar,
    created_date timestamp,
    updated_date timestamp,
    active       boolean,
    url_id       int,
    icon_id      int
);

create table digital.group
(
    id           SERIAL PRIMARY KEY,
    name         varchar,
    description  varchar,
    tribe        varchar,
    team         varchar,
    component    varchar,
    created_by   varchar,
    updated_by   varchar,
    created_date timestamp,
    updated_date timestamp,
    active       boolean
);


create table digital.user
(
    id     SERIAL PRIMARY KEY,
    name   varchar,
    email  varchar,
    active boolean
);

create table digital.icon
(
    id      SERIAL PRIMARY KEY,
    name    varchar,
    type    varchar,
    card_id int references digital.card(id),
    file    oid
);


create table digital.user_in_group
(
    id         SERIAL PRIMARY KEY,
    email      varchar,
    group_id   int references digital.group(id),
    added_date date,
    added_by   varchar
);


create table digital.url
(
    id           SERIAL PRIMARY KEY,
    long_url     varchar,
    created_date date,
    card_id      int references digital.card(id),
    short_url    varchar,
    expires_date date
);


create table digital.card_in_group
(
    id         SERIAL PRIMARY KEY,
    card_id    int references digital.card(id),
    group_id   int references digital.group(id),
    added_date date,
    added_by   varchar
);

create table digital.suggestion_queue
(
    id           SERIAL PRIMARY KEY,
    card_id     int references digital.card(id),
    email        varchar,
    suggested_date date,
    suggestion_text varchar
);

