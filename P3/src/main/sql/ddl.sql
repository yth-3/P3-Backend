DROP TABLE IF EXISTS user_roles CASCADE;
DROP TABLE IF EXISTS claim_types CASCADE;
DROP TABLE IF EXISTS claim_statuses CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS claims CASCADE;


CREATE TABLE user_roles (
	role_id VARCHAR(255) primary key,
	role VARCHAR(255) unique not null
);

CREATE TABLE claim_types (
	type_id VARCHAR(255) primary key,
	type VARCHAR(255) unique not null
);

CREATE TABLE claim_statuses (
	status_id VARCHAR(255) primary key,
	status VARCHAR(255) unique not null
);

CREATE TABLE users (
	user_id VARCHAR(255) primary key,
	username VARCHAR(255) unique not null,
	password VARCHAR(255) not null,
	email VARCHAR(255) unique not null,
	registered TIMESTAMP not null,
	is_active BOOLEAN not null,
	role_id VARCHAR(255) REFERENCES user_roles(role_id)
);

CREATE TABLE claims (
	claim_id VARCHAR(255) primary key,
	submitter_id VARCHAR(255) not null references users(user_id),
	submitted TIMESTAMP not null,
	claimed NUMERIC(10,2) not null,
	type_id VARCHAR(255) not null references claim_types(type_id),
	description VARCHAR(255) not null,
	receipt OID,
	resolver_id VARCHAR(255) references users(user_id),
	resolved TIMESTAMP,
	settled NUMERIC(10,2) not null,
	status_id VARCHAR(255) not null references claim_statuses(status_id)
);
