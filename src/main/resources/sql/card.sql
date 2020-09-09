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
    group_name        varchar,
    tribe             varchar,
    team              varchar,
    component         varchar,
    suggested_by      varchar,
    updated_date      date,
    updated_by        varchar,
    authorize         boolean
);
