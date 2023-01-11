-- Set Up User Roles in Database
INSERT INTO user_roles(role_id,role) VALUES('0', 'Patient');
INSERT INTO user_roles(role_id,role) VALUES('1', 'Nurse');
INSERT INTO user_roles(role_id,role) VALUES('2', 'Doctor');
INSERT INTO user_roles(role_id,role) VALUES('3', 'Insurer');
INSERT INTO user_roles(role_id,role) VALUES('4', 'Admin');
INSERT INTO user_roles(role_id,role) VALUES('5', 'Staff');

-- Set Up Claim Types in Database
INSERT INTO claim_types(type_id, type) VALUES('CONSULTATION', 'CONSULTATION');
INSERT INTO claim_types(type_id, type) VALUES('MEDICATION', 'MEDICATION');
INSERT INTO claim_types(type_id, type) VALUES('PROCEDURE', 'PROCEDURE');

-- Set Up Example Users
INSERT INTO users
(user_id,username,password,email,registered,is_active,role_id)
VALUES('admin','admin','$argon2i$v=19$m=65536,t=10,p=1$V801axe5AYOQ7Ur1jiJgsw$AOqsED3bbYs5ZTv+Ub9okVEv03q3nnVWq6m/RcmwUDs','admin@admin.com','2022-01-10 00:00:00',true,'4');
INSERT INTO users
(user_id,username,password,email,registered,is_active,role_id)
VALUES('insurer','insurer','$argon2i$v=19$m=65536,t=10,p=1$V801axe5AYOQ7Ur1jiJgsw$AOqsED3bbYs5ZTv+Ub9okVEv03q3nnVWq6m/RcmwUDs','insurer@insurer.com','2022-01-10 00:00:00',true,'3');
INSERT INTO users
(user_id,username,password,email,registered,is_active,role_id)
VALUES('doctor','doctor','$argon2i$v=19$m=65536,t=10,p=1$V801axe5AYOQ7Ur1jiJgsw$AOqsED3bbYs5ZTv+Ub9okVEv03q3nnVWq6m/RcmwUDs','doctor@doctor.com','2022-01-10 00:00:00',true,'2');
INSERT INTO users
(user_id,username,password,email,registered,is_active,role_id)
VALUES('nurse','nurse','$argon2i$v=19$m=65536,t=10,p=1$V801axe5AYOQ7Ur1jiJgsw$AOqsED3bbYs5ZTv+Ub9okVEv03q3nnVWq6m/RcmwUDs','nurse@nurse.com','2022-01-10 00:00:00',true,'1');
INSERT INTO users
(user_id,username,password,email,registered,is_active,role_id)
VALUES('staff','staff','$argon2i$v=19$m=65536,t=10,p=1$V801axe5AYOQ7Ur1jiJgsw$AOqsED3bbYs5ZTv+Ub9okVEv03q3nnVWq6m/RcmwUDs','staff@staff.com','2022-01-10 00:00:00',true,'5');
