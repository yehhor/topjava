DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (description, calories, datetime, user_id) VALUES
  ('Test1', 500, now() - '1 days'::INTERVAL, 100001),
  ('Test3', 500, now(), 100001),
  ('Test4', 500, now(), 100001),
  ('Test2', 2500, now(), 100001);