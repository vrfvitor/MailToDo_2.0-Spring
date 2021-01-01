INSERT INTO task (id, done, title, description, priority) VALUES (1, false, 'Enter Your Email', 'In the field above you can enter your email. Once you submit it, we will send emails periodically if there are High Priority Tasks you haven''t done yet!', 'HIGH');
INSERT INTO task (id, done, title, description, priority) VALUES (2, false, 'Sample Task', 'Clicking in options you''ll be able to update or delete the Task. Once it''s done you can click ''Done!'' and off it goes!', 'MEDIUM');

INSERT INTO tb_user (id, name, email, password) VALUES (1, 'Vitor', 'vrfvitor@hotmail.com', '$2a$10$YWOt6CQuueLl4I/Pp9lw7OfoqeZQzjfTGmQQPEnm3O6AX7x9ot/7K');
INSERT INTO tb_user (id, name, email, password) VALUES (2, 'Pedrin', 'pedro@gmail.com', '$2a$10$zB2AFv1dTX4Qg5fT2df.c.ld1xC7uR1lbGH7ZoMzWlmTuhEAmXQh.');

ALTER SEQUENCE task_id_seq RESTART WITH 3;
ALTER SEQUENCE tb_user_id_seq RESTART WITH 3;