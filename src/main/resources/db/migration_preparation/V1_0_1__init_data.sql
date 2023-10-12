insert into cloudstorage_diploma.users (username, password)
values ('oleg@gmail.com', '$2y$10$fmoXxjJKWz2hgCPXI9pkWeUV51KpbZhoagRBHBEQ53BR9FLdXLICO'),
       ('user@gmail.com', '$2y$10$OJyDv4/iMTSmLIIUPyEjVeriOphokSh.T4VbKAULS6bZv3MbfD4wS');

insert into cloudstorage_diploma.roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into cloudstorage_diploma.users_roles (user_id, role_id)
values (1, 2),
       (2, 1);