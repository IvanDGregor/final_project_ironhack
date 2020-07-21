insert into users(user_id,username, password) values
('12345678A','user', '$2a$10$P.EDNKPW25.IQyqFwVQ5.uwBfFEctsRuAFojgq1PFJh3k/3DPSMh2'),
('12345678B','admin','$2a$10$P.EDNKPW25.IQyqFwVQ5.uwBfFEctsRuAFojgq1PFJh3k/3DPSMh2');

insert into role (role, user_id) values
('ROLE_USER', '12345678A'),
('ROLE_ADMIN', '12345678B'),
('ROLE_USER', '12345678B');