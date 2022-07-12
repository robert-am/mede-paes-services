INSERT INTO `users` ( username, password, enable, first_name, last_name, email) values ('rmejia', '$2a$10$bG1G8RbTRsVFxi7tt6CySOcL2cv8XjrQUKvweQ/0n463YXFsiMNEe', 1, 'Robert', 'Mejia', 'rmejia@base16.co');
INSERT INTO `users` ( username, password, enable, first_name, last_name, email) values ('drestrepo', '$2a$10$8b7kveBPtNDgeCHuney9jO.7JK2axsGyQ9DQ750bxDV9VCaZRa3sS', 1, 'Diana', 'Restrepo', 'drestrepo@base16.co');
INSERT INTO `users` ( username, password, enable, first_name, last_name, email) values ('bgutierrez', '$2a$10$8b7kveBPtNDgeCHuney9jO.7JK2axsGyQ9DQ750bxDV9VCaZRa3sS', 1, 'Biviana', 'Gutierrez', 'bivi29@hotmail.com');
INSERT INTO `users` ( username, password, enable, first_name, last_name, email) values ('fmedina', '$2a$10$8b7kveBPtNDgeCHuney9jO.7JK2axsGyQ9DQ750bxDV9VCaZRa3sS', 1, 'Fernando', 'Medina', 'fmedina@ays.com.co');
INSERT INTO `users` ( username, password, enable, first_name, last_name, email) values ('jgarcia', '$2a$10$8b7kveBPtNDgeCHuney9jO.7JK2axsGyQ9DQ750bxDV9VCaZRa3sS', 1, 'Juan', 'Garcia', 'juanpasco13@hotmail.com');

INSERT INTO `roles` ( name ) values  ('ROLE_USER');
INSERT INTO `roles` ( name ) values  ('ROLE_ADMIN');

INSERT INTO `users_roles` values  (1,1);
INSERT INTO `users_roles` values  (2,1);
INSERT INTO `users_roles` values  (2,2);
INSERT INTO `users_roles` values  (3,1);
INSERT INTO `users_roles` values  (3,2);
INSERT INTO `users_roles` values  (4,1);
INSERT INTO `users_roles` values  (4,2);
INSERT INTO `users_roles` values  (5,1);
INSERT INTO `users_roles` values  (5,2);