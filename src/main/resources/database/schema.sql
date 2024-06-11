
drop table if exists country cascade;
drop table if exists region cascade;
drop table if exists city cascade;
drop table if exists address cascade;
drop table if exists users cascade;

create table if not exists country
(
    countryid serial  primary key,
    fullname character varying(30) not null unique,
    shortname character varying(10) not null
);

create table if not exists region
(
    regionid serial  primary key,
    countryidfk int,
    nameregion character varying(30) not null unique
);

create table if not exists city
(
    cityid serial  primary key,
    regionidfk integer,
    namecity character varying(20) not null unique
);

create table if not exists address
(
    addressid serial  primary key,
    cityidfk integer,
    person character varying(30) not null,
    street character varying(15) not null,
    building character varying(15) not null,
    office character varying(15) not null
);

create table if not exists users
(
    userid serial  primary key,
    username character varying(50) not null unique,
    password character varying(255) not null unique
);


alter table region
    add foreign key (countryidfk) references country(countryid) on delete cascade;

alter table city
    add  foreign key (regionidfk) references region(regionid) on delete cascade;

alter table address
    add foreign key (cityidfk) references city(cityid) on delete cascade;



