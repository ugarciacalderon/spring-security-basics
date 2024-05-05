drop database users;
drop table users;
create database users;

use users;

CREATE TABLE `users` (
                         `user_id` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(100) NOT NULL,
                         `password` varchar(500) NOT NULL,
                         `email` varchar(100) NOT NULL,
                         `mobile_number` varchar(20) NOT NULL,
                         `createdAt` DATETIME DEFAULT NULL,
                         `updatedAt` DATETIME null,
                         `enabled` INT not null,
                         PRIMARY KEY (`user_id`)
);

CREATE TABLE `authorities` (
                               `authId` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(50) NOT NULL,
                               `description` VARCHAR(25) not null,
                               `createdAt` DATETIME not null,
                               `updatedAt` DATETIME null,
                               `enabled` BOOL not null,
                               `user_id` int NOT NULL,
                               PRIMARY KEY (`authId`),
                               KEY `user_id` (`user_id`),
                               CONSTRAINT `userId_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
