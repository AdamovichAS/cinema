CREATE SCHEMA cinema;
USE cinema;

CREATE TABLE genre
(
    id      BIGINT AUTO_INCREMENT PRIMARY KEY ,
    name    VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE director
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY ,
    first_name  VARCHAR(255) NOT NULL ,
    last_name   VARCHAR(255) NOT NULL ,
    birth_date  DATE NOT NULL
);

CREATE TABLE film
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY ,
    director_id     BIGINT NOT NULL ,
    name            VARCHAR(255) NOT NULL ,
    release_date    DATE NOT NULL ,
    genre_id        BIGINT NOT NULL ,
    FOREIGN KEY (director_id) REFERENCES director(id),
    FOREIGN KEY (genre_id) REFERENCES genre(id)
);

INSERT INTO cinema.genre(name)
VALUES ('Комедия'),('Фэнтези'),('Боевик'),('Детектив'),('Фантастика'),('Триллер'),('Драмма');

INSERT INTO cinema.director(first_name, last_name, birth_date)
VALUES ('Оливье','Накшан','1973.04.15'),('Питер','Джексон','1961.10.31'),
       ('Райан','Джонсон','1973.12.17'),('Адамович','Антон','1992.10.10'),('Мартин','Скорсезе','1942.11.17'),
       ('Пол','Гринграсс','1955.08.13'),('Чак','Рассел','1952.08.06'),('Пол','Мак-Гиган','1963.09.19');

INSERT INTO cinema.film(director_id, name, release_date, genre_id)
VALUES (1,'1+1','2011.11.23',1),(2,'Властелин колец: Братство кольца','2001.12.10',2),
       (2,'Властелин колец: Две крепости','2002.12.05',2),(2,'Властелин колец: Возвращение Короля','2003.12.01',2),
       (3,'Достать ножи','2019.11.7',4),(5,'Отступники','2006.09.26',6),(6,'Ультиматум Борна','2007.06.25',3),
       (6,'Идентификация Борна','2007.06.25',3),(6,'Превосходство Борна','2007.06.25',3),(7,'Маска','1994.07.28',1),
       (8,'Счастливое число Слевина','2006.02.24',6),(5,'Банды Нью-Йорка','2002.12.09',7);