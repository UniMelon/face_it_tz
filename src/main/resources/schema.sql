create table universities (
    id int8 generated by default as identity,
    state varchar(255) not null,
    name varchar(255) not null,
    institution_type varchar(255) not null,
    phone varchar(255) not null,
    website varchar(255) not null,
    primary key (id)
);