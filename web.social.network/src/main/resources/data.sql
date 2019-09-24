INSERT INTO user (username, password, first_name, last_name)
VALUES
('tyrion@adm.ru' ,'$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Tyrion','Lannister');

INSERT INTO role (name)
VALUES 
('ROLE_USER'),
('ROLE_ADMIN');


INSERT INTO user_role (user_id, role_id)
VALUES 
(1, 1),
(1, 2);