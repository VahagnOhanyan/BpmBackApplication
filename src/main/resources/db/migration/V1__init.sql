create table pages
(
    id             bigint primary key AUTO_INCREMENT,
    name           varchar(100),
    url            varchar(500)  not null,
    parsing_X_path varchar(2000) not null

);
/*create sequence sq_pages_id start with 1 increment by 1;*/
insert into pages values(1, 'federalreserve','https://www.federalreserve.gov/','//*[@id="content"]/div[3]/div[1]/ul/li[1]/p/a/text()');

create table users
(
    login       varchar(100) primary key,
    password    varchar(100) not null,
    first_name  varchar(500) not null,
    last_name   varchar(500) not null,
    telegram_id varchar(50)  not null,
    role_id     bigint       not null

);
insert into users
values ('vahagn', '$2a$10$O07VKeKQHI5XOJjiWlqfwu/6kcrWMOfQAFdHicMYqilEqMn6MASs6', 'vahagn', 'ohanyan', '433547978', 2);

create table subscriptions
(
    id         bigint primary key AUTO_INCREMENT,
    user_login varchar(100) not null,
    page_id    bigint       not null
);
insert into subscriptions values(1, 'vahagn', 1);
/*create sequence sq_subscriptions_id start with 1 increment by 1;*/


create table parsing_results
(
    id                bigint primary key AUTO_INCREMENT,
    page_id           bigint not null,
    parsing_date_time timestamp,
    result            varchar(2000),
    sent              boolean

);
/*create sequence sq_parsing_results_id start with 1 increment by 1;*/

create table roles
(
    role_id   bigint primary key AUTO_INCREMENT,
    role_name varchar(2000) not null
);
/*create sequence sq_roles_id start with 1 increment by 1;*/
insert into roles
values (1, 'USER');
insert into roles
values (2, 'ADMIN');


create table privileges
(
    privilege_id   bigint primary key AUTO_INCREMENT,
    privilege_name varchar(2000) not null
);

/*create sequence sq_privileges_id start with 1 increment by 1;*/

insert into privileges
values (1, 'CanUpdatePassword');


create table user_privileges
(
    user_login   varchar(2000) not null,
    privilege_id bigint

);
insert into user_privileges
values ('vahagn', '1');
/*drop table parsing_results;
drop table subscriptions;
drop table privileges;
drop table roles;
drop table user_privileges;
drop table users;
drop table pages;
truncate flyway_schema_history;*/
