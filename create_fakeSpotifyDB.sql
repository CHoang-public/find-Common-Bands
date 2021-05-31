DROP DATABASE IF EXISTS fake_spotifyDB;
CREATE DATABASE fake_spotifyDB;
USE fake_spotifyDB;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

CREATE TABLE IF NOT EXISTS Users(
	
    userid int not null auto_increment,
    username varchar(64) not null unique,
    /*password varchar(64) not null,
    email varchar(100) not null,
    deleted bool DEFAULT false,*/
    
    constraint primary key(userid)
    
) engine=innodb;

CREATE TABLE IF NOT EXISTS Musicians(

	musician_id int not null primary key auto_increment,
    musician_name varchar(64) not null unique

)engine=innodb;

CREATE TABLE IF NOT EXISTS listens_to(

	userid int not null,
    musician_id int not null,

	CONSTRAINT PRIMARY KEY (userid, musician_id),
	CONSTRAINT FOREIGN KEY (userid) REFERENCES Users(userid),
    CONSTRAINT FOREIGN KEY (musician_id) REFERENCES Musicians(musician_id)
)engine=innodb;