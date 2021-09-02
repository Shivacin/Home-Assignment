create schema if not exists tempdb;
use tempdb;

create table IF NOT EXISTS condition (
	id bigint auto_increment,
	cond varchar(100),
	description varchar(512)
);

CREATE TABLE IF NOT EXISTS ITEM (
	id bigint auto_increment,
	title varchar(255),
	condition bigint,
	price decimal(10, 2),
	quantity int,
	description clob,
	foreign key (condition) references condition(id)
);

CREATE TABLE IF NOT EXISTS ITEM_IMAGE_URL (
	id bigint auto_increment,
	url varchar(512),
	item_id bigint,
	foreign key (item_id) references ITEM(id)
);

CREATE TABLE IF NOT EXISTS ITEM_SPECS (
	id bigint auto_increment,
	spec varchar(50),
	spec_val varchar(255),
	item_id bigint,
	foreign key (item_id) references ITEM(id)
);
