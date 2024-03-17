create table if not exists country
(
    id identity  primary key,
    fullname character varying(30) not null unique,
    shortname character varying(10) not null unique
);

create table if not exists region
(
  id identity  primary key,
  country_id  integer,
  nameregion character varying(30) not null unique
);

create table if not exists city
(
  id identity  primary key,
  region_id integer,
  namecity character varying(20) not null unique
);

create table if not exists address
(
    id identity  primary key,
    city_id integer,
    person character varying(30) not null,
    street character varying(15) not null,
    building character varying(15) not null,
    office character varying(15) not null
);

alter table region
    add foreign key (country_id) references country(id) on delete cascade;

alter table city
    add  foreign key (region_id) references region(id) on delete cascade;

alter table address
    add foreign key (city_id) references city(id) on delete cascade;

insert into country (fullname,shortname) values ('Россия','RU');
insert into country (fullname,shortname) values ('United States of America','USA');

insert into region ( country_id, nameregion) values (1,'Ростовская область');
insert into region ( country_id, nameregion) values (1,'Московская область');
insert into region ( country_id, nameregion) values (2,'BEBEBE');
insert into region ( country_id, nameregion) values (2,'BABABA');

insert into city(region_id, namecity) values (1,'Азов' );
insert into city(region_id, namecity) values (1,'Ростов-на-Дону' );
insert into city(region_id, namecity) values (2,'Москва' );
insert into city(region_id, namecity) values (3,'BUBUBU' );
insert into city(region_id, namecity) values (4,'BIBIBI' );

insert into address(city_id, person, street, building, office) values (1,'Вадим','Пушкина', 'Колотушкина','23');
insert into address(city_id, person, street, building, office) values (1,'ГАГА','Пушкина', 'Колотушкина','23');
insert into address(city_id, person, street, building, office) values (2,'ГЕГЕ','ХЕХЕХЕ', 'ХУХУХУ','ва');
insert into address(city_id, person, street, building, office) values (3,'ГУГУ','ХОХОХО', 'ХЫХЫХЫ','выыв');
insert into address(city_id, person, street, building, office) values (4,'BYBYBY','BSBSBS', '1','2');
insert into address(city_id, person, street, building, office) values (5,'BOBOBO','BCBCBC', '1','3');
insert into address(city_id, person, street, building, office) values (5,'BEBABU','BNBNBN', '1','3');