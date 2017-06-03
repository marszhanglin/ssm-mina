--mysql -uroot -p    --show databases;  
--创建数据库 minassm_1001
create database  minassm_1001;
use  minassm_1001;
drop database if exists users; 
create table users(
	id int primary key auto_increment,  
	name varchar(128),
	password varchar(128),
	email varchar(64),
	birthday date ,
	create_time timesTamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "创建时间" 
)character set utf8 collate utf8_general_ci;
-- 编码格式：character set utf8 ;排序规则：collate utf8_general_ci  

--插入默认记录
insert into users(name,password,email,birthday) values('name_no_001','123456','name_no_001@qq.com','1990-12-01');
insert into users(name,password,email,birthday) values('name_no_002','123456','name_no_002@qq.com','1990-12-02');
insert into users(name,password,email,birthday) values('name_no_003','123456','name_no_003@qq.com','1990-12-03');

--增加一个字段 存储iosession_id
--alter table users add iosession_id varchar(128); 

--参考：http://blog.csdn.net/arkblue/article/details/9070797 
--增加用户名及邮箱唯一约束   show create table users;
alter table users add unique key `name` ( name);
alter table users add unique key `email` ( email);



