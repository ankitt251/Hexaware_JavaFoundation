create database Ecommerce;
use Ecommerce;


create table User(
	UserId int primary key,
    UserName varchar(20),
    UserEmail varchar(20),
    UserContact int
);

Insert into User values(1, 'Ankit', 'test@gmail.com' , '2020202020');
Insert into User values(2, 'Manjeet', 'manjeet@gmail.com' , '252525255');
Insert into User values(3, 'Riya', 'riya@gmail.com' , '32152321');
Insert into User values(4, 'Chayan', 'chayan@gmail.com' , '1234567890');


select * from User;


select 2*4 as product;
select now();

select current_date;

select concat("Ankit", " ","Gorane") as Fullname;