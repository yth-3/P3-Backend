INSERT INTO user_roles
(role_id,role)
VALUES('0', 'Patient'),
('1', 'Nurse'),
('2', 'Doctor'),
('3', 'Insurer'),
('4', 'Admin');

INSERT INTO users
(user_id,username,password,email,registered,is_active,role_id)
VALUES('admin','admin','$argon2i$v=19$m=65536,t=10,p=1$V801axe5AYOQ7Ur1jiJgsw$AOqsED3bbYs5ZTv+Ub9okVEv03q3nnVWq6m/RcmwUDs','admin@admin.com','2022-01-10 00:00:00',true,'4'),
('insurer','insurer','insurer','insurer@insurer.com','2022-01-10 00:00:00',true,'3'),
('doctor','doctor','doctor','doctor@doctor.com','2022-01-10 00:00:00',true,'2'),
('nurse','nurse','nurse','nurse@nurse.com','2022-01-10 00:00:00',true,'1');
