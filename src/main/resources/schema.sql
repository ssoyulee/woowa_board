CREATE TABLE USER
(
	user_id varchar(255) not null,
	password varchar(255) not null,
	name varchar(255) not null,
	email varchar(255),
	ranking integer,
	del_yn varchar(1),
	regpe_id varchar(255),
	reg_dts timestamp,
	modpe_id varchar(255),
	mod_dts timestamp,
    primary key(user_id)
);

CREATE TABLE BOARD
(
	board_id integer not null,
	board_name varchar(255) not null,
	explanation varchar(255),
	del_yn varchar(1),
	regpe_id varchar(255),
	reg_dts timestamp,
	modpe_id varchar(255),
	mod_dts timestamp,
    primary key(board_id)
);

CREATE TABLE POST
(
	board_id integer not null,	
	post_id integer not null,
	post_title varchar(4000) not null,
	post_content clob not null,
	del_yn varchar(1),
	regpe_id varchar(255),
	reg_dts timestamp,
	modpe_id varchar(255),
	mod_dts timestamp,
    primary key(board_id,post_id)
);

CREATE TABLE COMMENT
(
	post_id integer not null,	
	comment_id integer not null,
	comment_content clob not null,
	del_yn varchar(1),
	regpe_id varchar(255),
	reg_dts timestamp,
	modpe_id varchar(255),
	mod_dts timestamp,
    primary key(post_id,comment_id)
);

