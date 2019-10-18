INSERT INTO role (name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO user (username, password, first_name, last_name, date_of_birth, sex, phone, image)
VALUES
('tyrion@lannister.ru',   '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Tyrion',   'Lannister', '1987-09-28', 1, '+7 999 999 99 99', '1.jpg'),
('cersei@lannister.ru',   '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Cersei',   'Lannister', '1978-09-28', 2, '+7 888 999 99 99', '2.png'),
('jaime@lannister.ru',    '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Jaime',    'Lannister', '1975-09-28', 1, '+7 777 999 99 99', '3.jpeg'),
('stark@north.no',        '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Ned',      'Stark',     '1965-11-15', 1, '+7 666 999 99 99', '4.jpg'),
('astark@north.no',       '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Sansa',    'Stark',     '1992-04-26', 2, '+7 555 999 99 99', '5.jpg'),
('aria.stark@north.no',   '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Aria',     'Stark',     '1996-12-15', 2, '+7 555 999 99 99', '6.jpg'),
('john@north.no',         '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Jon',      'Snow',      '1988-10-11', 1, '+7 444 999 99 99', '7.jpg'),
('dayneris@targarien.ta', '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Daenerys', 'Targaryen', '1981-03-17', 2, '+7 333 999 99 99', '8.jpg'),
('rob@north.no',          '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Rob',      'Stark',     '1988-10-13', 1, '+7 222 999 99 99', '9.jpg'),
('tywin@lannister.la',    '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Tywin',    'Lannister', '1952-08-27', 1, '+7 111 999 99 99', '10.jpg'),
('brienne@tarth.ta',      '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Brienne',  'Tarth',     '1967-02-19', 2, '+7 999 888 99 99', '11.jpg'),
('lord@varys.va',         '$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K', 'Lord',     'Varys',     '1954-12-01', 1, '+7 999 777 99 99', '12.jpeg');


INSERT INTO user_role (user_id, role_id)
VALUES 
(1, 1),
(1, 2),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1);

INSERT INTO friendship (id, user_sender, user_receiver, accepted)
VALUES
(null, 1,  2, true),
(null, 1,  3, true),
(null, 2,  3, true),
(null, 4,  5, true),
(null, 4,  6, true),
(null, 5,  1, false),
(null, 6,  1, false),
(null, 1, 10, false),
(null, 1,  8, false);