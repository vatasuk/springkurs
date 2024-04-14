create table if not exists country
(
    countryid identity  primary key,
    fullname character varying(30) not null unique,
    shortname character varying(10) not null unique
);

create table if not exists region
(
  regionid identity  primary key,
  countryidfk  integer,
  nameregion character varying(30) not null unique
);

create table if not exists city
(
  cityid identity  primary key,
  regionidfk integer,
  namecity character varying(20) not null unique
);

create table if not exists address
(
    addressid identity  primary key,
    cityidfk integer,
    person character varying(30) not null,
    street character varying(15) not null,
    building character varying(15) not null,
    office character varying(15) not null
);

create table if not exists users
(
    userid identity  primary key,
    username character varying(50) not null unique,
    password character varying(255) not null unique
);



alter table region
    add foreign key (countryidfk) references country(countryid) on delete cascade;

alter table city
    add  foreign key (regionidfk) references region(regionid) on delete cascade;

alter table address
    add foreign key (cityidfk) references city(cityid) on delete cascade;

insert into country (fullname,shortname) values ('Россия','RU');
insert into country (fullname,shortname) values ('United States of America','USA');

insert into region (countryidfk, nameregion) values (1,'Ростовская область');
insert into region (countryidfk, nameregion) values (1,'Московская область');
insert into region (countryidfk, nameregion) values (2,'BEBEBE');
insert into region (countryidfk, nameregion) values (2,'BABABA');

insert into city(regionidfk, namecity) values (1,'Азов' );
insert into city(regionidfk, namecity) values (1,'Ростов-на-Дону' );
insert into city(regionidfk, namecity) values (2,'Москва' );
insert into city(regionidfk, namecity) values (3,'BUBUBU' );
insert into city(regionidfk, namecity) values (4,'BIBIBI' );

insert into address(cityidfk, person, street, building, office) values (1,'Вадим','Пушкина', 'Колотушкина','23');
insert into address(cityidfk, person, street, building, office) values (1,'ГАГА','Пушкина', 'Колотушкина','23');
insert into address(cityidfk, person, street, building, office) values (2,'ГЕГЕ','ХЕХЕХЕ', 'ХУХУХУ','ва');
insert into address(cityidfk, person, street, building, office) values (3,'ГУГУ','ХОХОХО', 'ХЫХЫХЫ','выыв');
insert into address(cityidfk, person, street, building, office) values (4,'BYBYBY','BSBSBS', '1','2');
insert into address(cityidfk, person, street, building, office) values (5,'BOBOBO','BCBCBC', '1','3');
insert into address(cityidfk, person, street, building, office) values (5,'BEBABU','BNBNBN', '1','3');




insert into users(username, password) values ('vatasuk','$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by');

