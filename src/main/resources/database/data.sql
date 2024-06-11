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
