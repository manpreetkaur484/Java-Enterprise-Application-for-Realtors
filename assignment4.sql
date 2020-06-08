DROP DATABASE IF EXISTS assignment4;
CREATE DATABASE assignment4;
USE assignment4;

create table SEC_USER
(
  userId           BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userName         VARCHAR(36) NOT NULL UNIQUE,
  encryptedPassword VARCHAR(128) NOT NULL,
  ENABLED           BIT NOT NULL 
) ;

create table SEC_ROLE
(
  roleId   BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  roleName VARCHAR(30) NOT NULL UNIQUE
) ;

create table USER_ROLE
(
  ID      BIGINT NOT NULL Primary Key AUTO_INCREMENT,
  userId BIGINT NOT NULL,
  roleId BIGINT NOT NULL
);

alter table USER_ROLE
  add constraint USER_ROLE_UK unique (userId, roleId);

alter table USER_ROLE
  add constraint USER_ROLE_FK1 foreign key (userId)
  references SEC_USER (userId);
 
alter table USER_ROLE
  add constraint USER_ROLE_FK2 foreign key (roleId)
  references SEC_ROLE (roleId);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('buyer', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('realtor', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1);
 
insert into sec_role (roleName)
values ('ROLE_BUYER');
insert into sec_role (roleName)
values ('ROLE_REALTOR');

insert into user_role (userId, roleId)
values (1, 1);
insert into user_role (userId, roleId)
values (2, 2);

create table commercial_Property
(
  id           INT NOT NULL Primary Key AUTO_INCREMENT,
  address              VARCHAR(200) NOT NULL,
  propertyType         VARCHAR(150) NOT NULL,
  communityType        VARCHAR(150) NOT NULL,
  floorSpace           VARCHAR(150) NOT NULL,
  landSize             VARCHAR(100) NOT NULL,
  amenities            VARCHAR(200) NOT NULL,
  annualTax            int NOT NULL,
  transactionType      VARCHAR(200),
 Data MEDIUMBLOB
) ;

create table residential_Property
(
  id           INT NOT NULL Primary Key AUTO_INCREMENT,
  address            VARCHAR(200) NOT NULL,
  propertyType       VARCHAR(150) NOT NULL,
  buildingType       VARCHAR(150) NOT NULL,
  communityType      VARCHAR(150) NOT NULL,
  annualTax          int NOT NULL,
  parkingSpaces      int NOT NULL,
  bedrooms           int NOT NULL,
  bathrooms          int NOT NULL,
  basement           VARCHAR(20) NOT NULL,
  swimmingPool       VARCHAR(20) NOT NULL,
  amenities         VARCHAR(200) NOT NULL,
  Data MEDIUMBLOB
) ;


COMMIT;

