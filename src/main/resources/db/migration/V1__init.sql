CREATE table pages
(
    id             bigint primary key AUTO_INCREMENT,
    name           varchar(100),
    url            varchar(500)  not null,
    parsing_X_path varchar(2000) not null

);
/*create sequence sq_pages_id start with 1 increment by 1;*/
insert into pages VALUES(1, 'cbr','https://www.cbr.ru/','//div[@class=''main-indicator_rate''][2]/div[contains(@class,''mono-num'')][2]/text()');

CREATE table users
(
    login       varchar(100) primary key,
    password    varchar(100) not null,
    first_name  varchar(500) not null,
    last_name   varchar(500) not null,
    telegram_id varchar(50)  not null,
    role_id     bigint       not null

);
insert into users
VALUES ('vahagn', '$2a$10$O07VKeKQHI5XOJjiWlqfwu/6kcrWMOfQAFdHicMYqilEqMn6MASs6', 'vahagn', 'ohanyan', '433547978', 2);

CREATE table subscriptions
(
    id         bigint primary key AUTO_INCREMENT,
    user_login varchar(100) not null,
    page_id    bigint       not null
);
insert into subscriptions VALUES(1, 'vahagn', 1);
/*create sequence sq_subscriptions_id start with 1 increment by 1;*/


CREATE table parsing_results
(
    id                bigint primary key AUTO_INCREMENT,
    page_id           bigint not null,
    parsing_date_time timestamp,
    result            varchar(2000),
    sent              boolean

);
/*create sequence sq_parsing_results_id start with 1 increment by 1;*/

CREATE table roles
(
    role_id   bigint primary key AUTO_INCREMENT,
    role_name varchar(2000) not null
);
/*create sequence sq_roles_id start with 1 increment by 1;*/
insert into roles
VALUES (1, 'USER');
insert into roles
VALUES (2, 'ADMIN');


CREATE table privileges
(
    privilege_id   bigint primary key AUTO_INCREMENT,
    privilege_name varchar(2000) not null
);

/*create sequence sq_privileges_id start with 1 increment by 1;*/

insert into privileges
VALUES (1, 'CanUpdatePassword');


CREATE table user_privileges
(
    user_login   varchar(2000) not null,
    privilege_id bigint

);
insert into user_privileges
VALUES ('vahagn', '1');