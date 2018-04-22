CREATE DATABASE clientdb;
CREATE USER 'testuser'@'localhost' IDENTIFIED BY 'test';
GRANT ALL PRIVILEGES ON clientdb.* TO 'testuser'@'localhost';

use clientdb;

create table client_user(
    id bigint auto_increment primary key,
    username varchar(100),
    password varchar(50),
    access_token varchar(100) NULL,
    access_token_validity datetime NULL,
    refresh_token varchar(100) NULL
);

insert into client_user
(username, password)
value
('bobo', 'xyz');