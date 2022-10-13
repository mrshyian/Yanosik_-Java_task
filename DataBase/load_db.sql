DROP TABLE IF EXISTS public.users CASCADE;
CREATE TABLE public.users
(
    id bigint PRIMARY KEY NOT NULL,
    nick text NOT NULL,
    login text UNIQUE NOT NULL,
    password text NOT NULL,
    insert_time timestamp NOT NULL DEFAULT now()
);

DROP TABLE IF EXISTS public.vehicles CASCADE;
CREATE TABLE public.vehicles
(
    id bigint PRIMARY KEY NOT NULL,
    login text NOT NULL,
    brand text NOT NULL,
    model text NOT NULL,
    insert_time timestamp NOT NULL,
    FOREIGN KEY (login) REFERENCES users (login)
);

DROP TABLE IF EXISTS public.insurance_offers CASCADE;
CREATE TABLE public.insurance_offers
(
    id bigint PRIMARY KEY NOT NULL,
    vehicle_id bigint NOT NULL,
    insurer text NOT NULL,
    price float NOT NULL,
    insert_time timestamp NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);


INSERT INTO users (id, nick, login, password) VALUES (1, 'Kinder', 'kinder', '111111');
INSERT INTO users (id, nick, login, password) VALUES (2, 'Bueno', 'bueno', '222222');
INSERT INTO users (id, nick, login, password) VALUES (3, 'Snickers', 'snickers', '333333');

INSERT INTO vehicles (id, login, brand, model, insert_time) VALUES (1,'kinder', 'Mercedes', 'suv', '2018-11-04 11:11:11.156766');
INSERT INTO vehicles (id, login, brand, model, insert_time) VALUES (2, 'bueno', 'Volkswagen', 'polo', '2015-10-12 14:02:00.156766');
INSERT INTO vehicles (id, login, brand, model, insert_time) VALUES (3, 'bueno', 'Volkswagen', 'passat', '2015-10-12 15:28:11.156766');
INSERT INTO vehicles (id, login, brand, model, insert_time) VALUES (4, 'snickers', 'BMW', 'x5', '2022-10-12 19:08:15.156766');

INSERT INTO insurance_offers (id, vehicle_id, insurer, price, insert_time) VALUES (1, 1, 'insurer1', 3000.0, '2021-12-12 13:41:55.156766');
INSERT INTO insurance_offers (id, vehicle_id, insurer, price, insert_time) VALUES (2, 2, 'insurer2', 1000.0, '2022-10-11 17:11:17.156766');
INSERT INTO insurance_offers (id, vehicle_id, insurer, price, insert_time) VALUES (3, 3, 'insurer2', 2500.0, '2022-10-11 18:23:46.156766');
INSERT INTO insurance_offers (id, vehicle_id, insurer, price, insert_time) VALUES (4, 4, 'insurer3', 4000.0, '2022-08-23 19:08:15.156766');
