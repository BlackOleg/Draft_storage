CREATE SCHEMA IF NOT EXISTS cloudstorage_diploma;

create table IF NOT EXISTS cloudstorage_diploma.users
(
    id       bigserial    not null,
    username varchar(50) not null
    constraint users_login_key
    unique,
    password varchar(80) not null,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

create table IF NOT EXISTS cloudstorage_diploma.roles
(
    id   serial
    constraint pk_roles
    primary key,
    name varchar(50)
);

create table IF NOT EXISTS cloudstorage_diploma.users_roles
(
    user_id bigint not null
    constraint user_id_fk
    references cloudstorage_diploma.users ,
    role_id int not null
    constraint role_id_fk
    references cloudstorage_diploma.roles ,
    constraint user_role
    primary key (user_id, role_id)
    );

create table IF NOT EXISTS cloudstorage_diploma.files
(
    id        bigserial,
    filename varchar(255),
    date      date,
    type      varchar(255),
    file_data oid,
    size      bigint,
    user_id   bigint not null
        constraint user_id
            references cloudstorage_diploma.users,
    CONSTRAINT pk_files PRIMARY KEY (id)
);

