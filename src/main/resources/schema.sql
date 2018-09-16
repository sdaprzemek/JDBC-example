DROP DATABASE IF EXISTS jdbc_example;
CREATE DATABASE jdbc_example;
USE jdbc_example;

CREATE TABLE books (
    id BIGINT AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    number_of_pages INT,
    has_hard_cover BIT(1),
    release_date DATE,

    PRIMARY KEY(id)
);

INSERT INTO books
        (title, number_of_pages, has_hard_cover, release_date)
    VALUES
        ('Just Hibernate', 140, b'0', '2014-06-10'),
        ('Hibernate: A Developers Notebook', 192, b'1', '2004-05-20');
