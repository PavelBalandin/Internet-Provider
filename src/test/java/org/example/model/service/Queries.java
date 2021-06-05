package org.example.model.service;

public interface Queries {
    String CREATE_TABLES = "----------------------------------------------------------------\n" +
            "-- ROLES\n" +
            "----------------------------------------------------------------\n" +
            "CREATE TABLE roles\n" +
            "(\n" +
            "    id SERIAL NOT NULL PRIMARY KEY,\n" +
            "    name VARCHAR(30) NOT NULL UNIQUE\n" +
            ");\n" +
            "\n" +
            "----------------------------------------------------------------\n" +
            "-- STATUSES\n" +
            "----------------------------------------------------------------\n" +
            "CREATE TABLE statuses\n" +
            "(\n" +
            "    id SERIAL NOT NULL PRIMARY KEY,\n" +
            "    name VARCHAR(30) NOT NULL UNIQUE\n" +
            ");\n" +
            "\n" +
            "----------------------------------------------------------------\n" +
            "-- USERS\n" +
            "----------------------------------------------------------------\n" +
            "CREATE TABLE users\n" +
            "(\n" +
            "    id SERIAL NOT NULL PRIMARY KEY,\n" +
            "    login VARCHAR(30) NOT NULL UNIQUE,\n" +
            "    password VARCHAR(100) NOT NULL,\n" +
            "    firstname VARCHAR(30) NOT NULL,\n" +
            "    lastname VARCHAR(30) NOT NULL,\n" +
            "    role_id INTEGER NOT NULL REFERENCES roles(id) DEFAULT 1,\n" +
            "    status_id INTEGER NOT NULL REFERENCES statuses(id) DEFAULT 1\n" +
            ");\n" +
            "\n" +
            "----------------------------------------------------------------\n" +
            "-- SERVICES\n" +
            "----------------------------------------------------------------\n" +
            "CREATE TABLE services\n" +
            "(\n" +
            "    id SERIAL NOT NULL PRIMARY KEY,\n" +
            "    name VARCHAR(30) NOT NULL UNIQUE\n" +
            ");\n" +
            "\n" +
            "----------------------------------------------------------------\n" +
            "-- TARIFFS\n" +
            "----------------------------------------------------------------\n" +
            "CREATE TABLE tariffs\n" +
            "(\n" +
            "    id SERIAL NOT NULL PRIMARY KEY,\n" +
            "    name VARCHAR(30) NOT NULL,\n" +
            "    description VARCHAR(200) NOT NULL,\n" +
            "    duration integer NOT NULL,\n" +
            "    price NUMERIC(19,2) NOT NULL,\n" +
            "    service_id INTEGER NOT NULL REFERENCES services(id) ON DELETE CASCADE\n" +
            ");\n" +
            "\n" +
            "----------------------------------------------------------------\n" +
            "-- payments\n" +
            "----------------------------------------------------------------\n" +
            "CREATE TABLE payments\n" +
            "(\n" +
            "    id SERIAL NOT NULL PRIMARY KEY,\n" +
            "    payment NUMERIC(19,2) NOT NULL,\n" +
            "    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,\n" +
            "    payment_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP \n" +
            ");\n" +
            "\n" +
            "----------------------------------------------------------------\n" +
            "-- USER SERVICE TARIFF\n" +
            "----------------------------------------------------------------\n" +
            "CREATE TABLE user_tariff\n" +
            "(\n" +
            "    user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,\n" +
            "    tariff_id INTEGER NOT NULL REFERENCES tariffs(id) ON DELETE CASCADE,\n" +
            "    date_begin TIMESTAMPTZ NOT NULL,\n" +
            "\tdate_end TIMESTAMPTZ NOT NULL\n" +
            ");\n" +
            "\n" +
            "----------------------------------------------------------------\n" +
            "-- TEST DATA\n" +
            "----------------------------------------------------------------\n" +
            "INSERT INTO roles VALUES(DEFAULT, 'USER');\n" +
            "INSERT INTO roles VALUES(DEFAULT, 'ADMIN');\n" +
            "\n" +
            "INSERT INTO statuses VALUES(DEFAULT, 'ENABLED');\n" +
            "INSERT INTO statuses VALUES(DEFAULT, 'DISABLED');\n" +
            "\n" +
            "INSERT INTO users VALUES(DEFAULT, 'Admin', '21232F297A57A5A743894A0E4A801FC3', 'Mikhail', 'Kutuzov', 2 , 1);\n" +
            "INSERT INTO users VALUES(DEFAULT, 'Yaroslav', 'E2D1D3F22F3782F45F9B2C3C0B013D13', 'Yaroslav', 'Panov', 1 , 1);\n" +
            "INSERT INTO users VALUES(DEFAULT, 'Pasha', 'EC8BC8E2B120D143E7274DE2508F3F6F', 'Pavel', 'Balandin', 1 , 1);\n" +
            "INSERT INTO users VALUES(DEFAULT, 'Dasha', '4BEA249142664D11A289FFDF30225A91', 'Darya', 'Petrenko', 1 , 1);\n" +
            "INSERT INTO users VALUES(DEFAULT, 'Sasha', '481F693417E9A74E783CAEA72063B606', 'Oleksandr', 'Novikov', 1 , 1);\n" +
            "INSERT INTO users VALUES(DEFAULT, 'Masha', 'C3CC6E312D2BAD42CF535AAC3A259ABD', 'Mariya', 'Sidorova', 1 , 1);\n" +
            "INSERT INTO users VALUES(DEFAULT, 'Sergey', 'D947F2DEF6D2F32C2FC7DF910ED00600', 'Sergey', 'Voronin', 1 , 1);\n" +
            "\n" +
            "INSERT INTO services VALUES(DEFAULT, 'Телефон');\n" +
            "INSERT INTO services VALUES(DEFAULT, 'Інтернет');\n" +
            "INSERT INTO services VALUES(DEFAULT, 'Кабельне ТБ');\n" +
            "INSERT INTO services VALUES(DEFAULT, 'IP-TV');\n" +
            "\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Light', '200 хвилини, 1000 мб', 30, 50, 1);\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Light +', '300 хвилини, 1500 мб', 30, 75, 1);\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Standard', '400 хвилини, 2000 мб', 30, 100, 1);\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Ultimate', 'Безліміт на всіх операторів, 4000 мб', 30, 200, 1);\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Ultimate Pro', 'Безліміт на всіх операторів, Безліміт інтернет', 30, 500, 1);\n" +
            "\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Start', 'до 50 Мбіт/с', 30, 100, 2);\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Ultra', 'до 100 Мбіт/с', 30, 200, 2);\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Mega','до 1 Гбіт/с', 30, 399, 2);\n" +
            "\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'Start TV', '140 каналів', 30, 255, 3);\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'VIP TV', '200 каналів', 30, 320, 3);\n" +
            "\n" +
            "INSERT INTO tariffs VALUES (DEFAULT, 'VIP', 'до 100 Мбіт/с, 140 каналів', 60, 400, 4);\n" +
            "\n" +
            "\n" +
            "INSERT INTO user_tariff VALUES(2,1, current_date, current_date + interval '30 days');\n" +
            "INSERT INTO user_tariff VALUES(2,6, current_date, current_date + interval '30 days');\n" +
            "INSERT INTO user_tariff VALUES(3,7, current_date, current_date + interval '30 days');\n" +
            "INSERT INTO user_tariff VALUES(4,10, current_date, current_date + interval '30 days');\n" +
            "INSERT INTO user_tariff VALUES(4,11, current_date, current_date + interval '30 days');\n" +
            "\n" +
            "INSERT INTO payments VALUES(DEFAULT, 400, 2, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 100, 2, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 200, 2, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 2000, 3, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 400, 3, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 100, 3, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 200, 3, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 200, 4, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 200, 4, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 200, 4, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 600, 5, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 600, 5, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 600, 5, DEFAULT);\n" +
            "INSERT INTO payments VALUES(DEFAULT, 600, 5, DEFAULT);\n" +
            "\n" +
            "CREATE OR REPLACE FUNCTION make_order(varchar(30), int[])\n" +
            "RETURNS numeric AS\n" +
            "$$\n" +
            "DECLARE\n" +
            "current_user_id int; -- current user id\n" +
            "psum numeric; -- user payments sum\n" +
            "tsum numeric; -- tariffs sum\n" +
            "tariff tariffs%rowtype; -- tariff\n" +
            "BEGIN\n" +
            "SELECT u.id into current_user_id FROM users u where u.login = $1;\n" +
            "SELECT sum(payment) into psum FROM payments p where p.user_id = current_user_id;\n" +
            "SELECT sum(price) into tsum FROM tariffs t where t.id = ANY($2);\n" +
            "IF (SELECT ARRAY(SELECT ut.tariff_id FROM user_tariff ut WHERE user_id = current_user_id)) && $2 THEN\n" +
            "\traise exception 'User have already had the tariffs';\n" +
            "END IF;\n" +
            "IF psum - tsum >= 0 THEN\n" +
            "  FOR tariff IN\n" +
            "    SELECT * FROM tariffs t where t.id = ANY($2)\n" +
            "  LOOP\n" +
            "    INSERT INTO user_tariff VALUES(current_user_id, tariff.id, current_date, current_date + (tariff.duration * interval '1 day'));\n" +
            "  END LOOP;\n" +
            "  INSERT INTO payments VALUES(DEFAULT, -tsum, current_user_id, DEFAULT);\n" +
            "END IF;\n" +
            "RETURN psum - tsum;\n" +
            "END;\n" +
            "$$\n" +
            "LANGUAGE 'plpgsql'";

    String DELETE_TABLES = "DROP TABLE IF EXISTS payments CASCADE;\n" +
            "DROP TABLE IF EXISTS roles CASCADE;\n" +
            "DROP TABLE IF EXISTS services CASCADE;\n" +
            "DROP TABLE IF EXISTS statuses CASCADE;\n" +
            "DROP TABLE IF EXISTS tariffs CASCADE;\n" +
            "DROP TABLE IF EXISTS user_tariff CASCADE;\n" +
            "DROP TABLE IF EXISTS users CASCADE;";
}
