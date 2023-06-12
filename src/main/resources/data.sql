insert into users (name, username, password)
values ('Jhon', 'john@gmail.com', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       ('Michael', 'michael@yahoo.com', '$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m');

insert into tasks (title, description, status, expiration_date)
values ('Buy products for cake', null, 'NEW', '2023-01-29 12:00:00'),
       ('Do homework', 'Math and physics', 'IN_PROGRESS', '2023-01-31 10:30:00'),
       ('Clean rooms', null, 'DONE', null),
       ('Call mother', 'Ask about weekend', 'NEW', '2023-02-01 15:45:00');

insert into users_tasks (task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 2),
       (4, 1);

insert into users_roles (user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');