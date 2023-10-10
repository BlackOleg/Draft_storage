insert into cloudstorage_diploma.users (username, password)
values ('oleg@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6Im9sZWdAZ21haWwuY29tIiwicGFzc3dvcmQiOiJvbGVnIn0.4SwQOwj4GUDQzjm2KLgOxJeSjXO5HlSJjx6VvOcLC8Q'),
       ('user@gmail.com', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InVzZXJAZ21haWwuY29tIiwicGFzc3dvcmQiOiJ1c2VyIn0.9mjZclXTk6AgpvYE3WLL2DnOFQHjNJQzc7CRwC7B7WU');

insert into cloudstorage_diploma.roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into cloudstorage_diploma.users_roles (user_id, role_id)
values (1, 2),
       (2, 1);