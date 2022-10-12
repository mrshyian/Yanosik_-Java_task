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
    insert_time timestamp NOT NULL DEFAULT now(),
    FOREIGN KEY (login) REFERENCES users (login)
);

DROP TABLE IF EXISTS public.insurance_offers CASCADE;
CREATE TABLE public.insurance_offers
(
    id bigint PRIMARY KEY NOT NULL,
    vehicle_id bigint NOT NULL,
    insurer text NOT NULL,
    price float NOT NULL,
    insert_time timestamp NOT NULL DEFAULT now(),
    FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);


INSERT INTO users (id, nick, login, password) VALUES (1, 'Kinder', 'kinder-dog', '111111');
INSERT INTO users (id, nick, login, password) VALUES (2, 'Bueno', 'bueno-dog', '222222');
INSERT INTO users (id, nick, login, password) VALUES (3, 'Snickers', 'snickers-dog', '333333');

INSERT INTO vehicles (id, login, brand, model) VALUES (1,'kinder-dog', 'Mercedes', 'suv');
INSERT INTO vehicles (id, login, brand, model) VALUES (2, 'bueno-dog', 'Volkswagen', 'polo');
INSERT INTO vehicles (id, login, brand, model) VALUES (3, 'bueno-dog', 'Volkswagen', 'passat');
INSERT INTO vehicles (id, login, brand, model) VALUES (4, 'snickers-dog', 'BMW', 'x5');

INSERT INTO insurance_offers (id, vehicle_id, insurer, price) VALUES (1, 1, 'insurer1', 3000.0);
INSERT INTO insurance_offers (id, vehicle_id, insurer, price) VALUES (2, 2, 'insurer2', 1000.0);
INSERT INTO insurance_offers (id, vehicle_id, insurer, price) VALUES (3, 3, 'insurer2', 2500.0);
INSERT INTO insurance_offers (id, vehicle_id, insurer, price) VALUES (4, 4, 'insurer3', 4000.0);
