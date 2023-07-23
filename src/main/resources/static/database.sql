DROP DATABASE IF EXISTS pan_secure;

CREATE DATABASE  pan_secure;

CREATE TABLE cards (token_id int PRIMARY KEY auto_increment,token varchar(200) not null,pan varchar(16) not null);
