----------------------------------------------------------------
-- ROLES
----------------------------------------------------------------
CREATE TABLE roles
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

----------------------------------------------------------------
-- STATUSES
----------------------------------------------------------------
CREATE TABLE statuses
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

----------------------------------------------------------------
-- USERS
----------------------------------------------------------------
CREATE TABLE users
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    login VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    firstname VARCHAR(30) NOT NULL,
    lastname VARCHAR(30) NOT NULL,
    role_id INTEGER NOT NULL REFERENCES roles(id) DEFAULT 1,
    status_id INTEGER NOT NULL REFERENCES statuses(id) DEFAULT 1
);

----------------------------------------------------------------
-- SERVICES
----------------------------------------------------------------
CREATE TABLE services
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE
);

----------------------------------------------------------------
-- TARIFFS
----------------------------------------------------------------
CREATE TABLE tariffs
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(30) NOT NULL UNIQUE,
    description VARCHAR(200) NOT NULL,
    duration VARCHAR(30) NOT NULL,
    price INTEGER NOT NULL,
    service_id INTEGER NOT NULL REFERENCES services(id) ON DELETE CASCADE
);

----------------------------------------------------------------
-- ACCOUNTS
----------------------------------------------------------------
CREATE TABLE accounts
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    payment INTEGER NOT NULL,
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE
);

----------------------------------------------------------------
-- USER SERVICE TARIFF
----------------------------------------------------------------
CREATE TABLE user_tariff
(
    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    tariff_id INTEGER NOT NULL REFERENCES tariffs(id) ON DELETE CASCADE,
    date_begin TIMESTAMPTZ NOT NULL,
	date_end TIMESTAMPTZ NOT NULL
);

----------------------------------------------------------------
-- TEST DATA
----------------------------------------------------------------
INSERT INTO roles VALUES(DEFAULT, 'USER');
INSERT INTO roles VALUES(DEFAULT, 'ADMIN');

INSERT INTO statuses VALUES(DEFAULT, 'ENABLED');
INSERT INTO statuses VALUES(DEFAULT, 'DISABLED');

INSERT INTO users VALUES(DEFAULT, 'Admin', 'admin', 'First name', 'Last name', 2 , 1);
INSERT INTO users VALUES(DEFAULT, 'User', 'user', 'User first name', 'User last name', 1 , 1);
INSERT INTO users VALUES(DEFAULT, 'Pasha', 'pasha', 'Pavel', 'Balandin', 1 , 1);
INSERT INTO users VALUES(DEFAULT, 'Dasha', 'dasha', 'Darya', 'Petrenko', 1 , 1);
INSERT INTO users VALUES(DEFAULT, 'Sasha', 'sasha', 'Oleksandr', 'Novikov', 1 , 1);
INSERT INTO users VALUES(DEFAULT, 'Masha', 'masha', 'Mariya', 'Sidorova', 1 , 1);
INSERT INTO users VALUES(DEFAULT, 'Sergey', 'sergey', 'Sergey', 'Voronin', 1 , 1);

INSERT INTO services VALUES(DEFAULT, 'Телефон');
INSERT INTO services VALUES(DEFAULT, 'Інтернет');
INSERT INTO services VALUES(DEFAULT, 'Кабельне ТБ');
INSERT INTO services VALUES(DEFAULT, 'IP-TV');

INSERT INTO tariffs VALUES (DEFAULT, 'Light', '200 хвилини, 1000 мб', 30, 50, 1);
INSERT INTO tariffs VALUES (DEFAULT, 'Light +', '300 хвилини, 1500 мб', 30, 75, 1);
INSERT INTO tariffs VALUES (DEFAULT, 'Standard', '400 хвилини, 2000 мб', 30, 100, 1);
INSERT INTO tariffs VALUES (DEFAULT, 'Ultimate', 'Безліміт на всіх операторів, 4000 мб', 30, 200, 1);
INSERT INTO tariffs VALUES (DEFAULT, 'Ultimate Pro', 'Безліміт на всіх операторів, Безліміт інтернет', 30, 500, 1);

INSERT INTO tariffs VALUES (DEFAULT, 'Start', 'до 50 Мбіт/с', 30, 100, 2);
INSERT INTO tariffs VALUES (DEFAULT, 'Ultra', 'до 100 Мбіт/с', 30, 200, 2);
INSERT INTO tariffs VALUES (DEFAULT, 'Mega','до 1 Гбіт/с', 30, 399, 2);

INSERT INTO tariffs VALUES (DEFAULT, 'Start TV', '140 каналів', 30, 255, 3);
INSERT INTO tariffs VALUES (DEFAULT, 'VIP TV', '200 каналів', 30, 320, 3);

INSERT INTO tariffs VALUES (DEFAULT, 'VIP', 'до 100 Мбіт/с, 140 каналів', 30, 400, 4);


INSERT INTO user_tariff VALUES(2,1, current_date, current_date + interval '30 days');
INSERT INTO user_tariff VALUES(2,6, current_date, current_date + interval '30 days');
INSERT INTO user_tariff VALUES(3,7, current_date, current_date + interval '30 days');
INSERT INTO user_tariff VALUES(4,10, current_date, current_date + interval '30 days');
INSERT INTO user_tariff VALUES(4,11, current_date, current_date + interval '30 days');