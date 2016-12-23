drop table people if exists;
create table people (id numeric IDENTITY, name varchar(100 CHARACTERS) not null, birthdate date not null, email varchar(100 CHARACTERS) not null,
    hash VARCHAR(64 CHARACTERS) NOT NULL);
ALTER TABLE people ADD CONSTRAINT people_hash_unique UNIQUE (hash);

drop table categories if exists;
create table categories (idCategory numeric IDENTITY, description varchar(100 CHARACTERS) not null, name varchar(100) not null);
insert into categories(description,name) values('Technology department','Technology');

drop table products if exists;
create table products (id numeric IDENTITY, description varchar(100 CHARACTERS) not null, code varchar(15) not null, price numeric(10,2) not null, amount numeric not null,
    hash VARCHAR(64 CHARACTERS) NOT NULL, idCategory numeric);
ALTER TABLE products ADD CONSTRAINT products_hash_unique UNIQUE (hash);
insert into products values(1,'Tablet Vulcan 8 Pulgadas 16 GB','T1245',1799,6,'1242677625412dfh',0);
