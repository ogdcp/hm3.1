create table car(
id serial primary key,
brand varchar(30),
model varchar(30),
price numeric(20,2)
)

create table car owner(
id serial primary key,
name varchar(50),
age serial,
has license boolean default false,
car id serial references car(id)
)