/*  Начало таблицы */
DROP TABLE IF EXISTS users;
CREATE TABLE users (
                      user_id serial NOT NULL,
                      active boolean,
                      email varchar(255),
                      first_name varchar(255),
                      last_name varchar(255),
                      password varchar(255),
                      PRIMARY KEY (user_id)
);

INSERT INTO users (active, email, first_name, last_name, password)
VALUES
('1','admin@admin.ru','Admin','Admin','$2a$10$owJmx.kZrTQ5z7GK3JMsEuo472A1.cP3YP8AiCzD/zHR4KMlXtHOm'),
('1','medic1@medic.ru', 'Medic' , 'Medic', '$2a$10$itZMAA/Lq5VEvn7ScIIqjOkq5NORVCX5M6jXzY9ckhnvAQie3SR7S'),
('1','medic2@medic.ru', 'Medic' , 'Medic', '$2a$10$QwPg7cydmWvgl7fRa9RLFuD5zVXiyrVoozbh2KcXJJMSoZecR/F12'),
('1','medic3@medic.ru', 'Medic' , 'Medic', '$2a$10$po/wfMY8itdBW7pcf/gcROHOBlD1Id4YVYtsdZT.rxeDL7Gp4saY6'),
('1','user1@user.ru', 'User' , 'User', '$2a$10$eMNlLr5P68AwPe8T0EdcteLqWrOQiaKlXoZEf6UzQ1J8TJJOKaEEW'),
('1','user2@user.ru', 'User' , 'User', '$2a$10$KLY/1wrco1pIxQ9B7CCuRuh0azoptUgIdZjnhX8P2hnC3Uwqjh2Xu'),
('1','user3@user.ru', 'User' , 'User', '$2a$10$VB9C1Z5auMjjmzwagI8FGONFRwOmzFQg33qvFYr9PB55CpmUkggHW');


/*  Начало таблицы */
DROP TABLE IF EXISTS  medic_roles;
CREATE TABLE  medic_roles (
                              medic_id             serial,
                              role           VARCHAR(50),
                              PRIMARY KEY (medic_id)

);

INSERT INTO medic_roles (medic_id , role)
VALUES
(1, 'Отсутствует'),
(2, 'Терапевт'),
(3, 'Отоларинголог'),
(4, 'Хирург'),
(5, 'Ортопед'),
(6, 'Невролог'),
(7, 'Кардиолог'),
(8, 'Окулист'),
(9, 'Уролог');



/*  Начало таблицы */
drop table if exists roles;
CREATE TABLE roles (
                      role_id               serial,
                      name                  VARCHAR(50),
                      PRIMARY KEY (role_id)
);

INSERT INTO roles (name)
VALUES
('ADMIN'), ('MEDIC'), ('USER');




/*  Начало таблицы */
drop table if exists users_roles;
CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id)
        REFERENCES users (user_id),
    FOREIGN KEY (role_id)
        REFERENCES roles (role_id)
);


INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 3);



/*  Начало таблицы */
DROP TABLE IF EXISTS patient_exams;
CREATE TABLE patient_exams (
                               exam_id                    bigserial,
                               email                 VARCHAR(50),
                               date                  VARCHAR(50),
                               petition              VARCHAR,
                               anamnez               VARCHAR,
                               common_data           VARCHAR,
                               diagnosis             VARCHAR,
                               advice                VARCHAR,
                               PRIMARY KEY (exam_id)

);


create table picture(id bigserial NOT NULL,
                     image_data  bytea not null,
                     image_name  VARCHAR(50) not null,
                     primary key (id));



DROP TABLE IF EXISTS exam_picture;
CREATE TABLE exam_picture (
                                 exam_id   INT NOT NULL,
                                 picture_id   INT NOT NULL,
                                 PRIMARY KEY (exam_id, picture_id),
                                 foreign key (exam_id) REFERENCES patient_exams (exam_id),
                                 foreign key (picture_id) REFERENCES picture (id)
);




