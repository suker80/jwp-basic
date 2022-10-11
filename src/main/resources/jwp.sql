DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS
(
    userId   varchar(12) NOT NULL,
    password varchar(12) NOT NULL,
    name     varchar(20) NOT NULL,
    email    varchar(50),

    PRIMARY KEY (userId)
);

INSERT INTO USERS
VALUES ('admin', 'password', '자바지기', 'admin@slipp.net');

drop table if exists QUESTIONS;
create table QUESTIONS
(
    questionId    bigint auto_increment,
    writer        varchar(50)   NOT NULL,
    title         varchar(50)   not null,
    contents      varchar(5000) not null,
    createdDate   timestamp     NOT NULL,
    countOfAnswer int,
    primary key (questionId)
);
Insert into QUESTIONS(writer, title, contents, createdDate, countOfAnswer)
VALUES ('asd', 'asd', 'asd', current_time(), 0);

drop table if exists ANSWERS;
create table ANSWERS
(
    answerId    bigint auto_increment not null,
    writer      varchar(50)           NOT NULL,
    contents    varchar(5000)         not null,
    createdDate timestamp             NOT NULL,
    questionId  bigint                not null,
    primary key (answerId)
);
