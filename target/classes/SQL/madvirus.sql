create schema spring4fs;
use spring4fs;

create table member(
	id int auto_increment primary key,
    email varchar(255) unique key,
    password varchar(100),
    name varchar(100),
    regdate datetime
);

insert into member(email, password, name ,regdate)
values ('hell@namver.com', 1234, 'hell', now());

select * from member;