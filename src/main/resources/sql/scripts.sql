create database databank;

use databank;

create table `users` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `username` VARCHAR(25) not null,
                         `password` VARCHAR(25) not null,
                         `enabled` INT not null,
                         primary key (`id`)
);

create table `authorities` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `username` VARCHAR(25) not null,
                               `authority` VARCHAR(25) not null,
                               primary key (`id`)
);

create table `customer` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `email` VARCHAR(25) not null,
                            `password` VARCHAR(25) not null,
                            `role` VARCHAR(25) not null,
                            primary key (`id`)
);

insert ignore into `users` values (null,'ulises_gc','ygsadyas','1');
insert ignore into `authorities` values (null,'ulises_gc','write');
insert ignore into `customer` values (null,'ugarciacalderon@gmail.com','12345','admin');


CREATE TABLE `authorities` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `customer_id` int NOT NULL,
                               `name` varchar(50) NOT NULL,
                               PRIMARY KEY (`id`),
                               KEY `customer_id` (`customer_id`),
                               CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
);

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'VIEWACCOUNT');

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'VIEWCARDS');

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'VIEWLOANS');

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'VIEWBALANCE');

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'ROLE_USER');

INSERT INTO `authorities` (`customer_id`, `name`)
VALUES (1, 'ROLE_ADMIN');

