CREATE TABLE USER
(
	user_id varchar(100) not null,
	password varchar(100) not null,
	name varchar(100) not null,
	email varchar(500),
	ranking integer,
	del_yn varchar(1),
	role varchar(10),
	session_id varchar(500), 
	regpe_id varchar(100),
	reg_dts timestamp,
	modpe_id varchar(100),
	mod_dts timestamp,
    primary key(user_id)
);

CREATE TABLE BOARD
(
	board_id integer not null,
	board_name varchar(500) not null,
	explanation varchar(4000),
	del_yn varchar(1),
	regpe_id varchar(255),
	reg_dts timestamp,
	modpe_id varchar(255),
	mod_dts timestamp,
    primary key(board_id)
);

CREATE TABLE POST
(
	post_id integer not null,
	board_id integer not null,
	post_title varchar(4000) not null,
	post_content clob not null,
	del_yn varchar(1),
	regpe_id varchar(255),
	reg_dts timestamp,
	modpe_id varchar(255),
	mod_dts timestamp,
    primary key(post_id)
);

CREATE TABLE COMMENT
(
	comment_id integer not null,
	post_id integer not null,
	comment_content clob not null,
	del_yn varchar(1),
	regpe_id varchar(255),
	reg_dts timestamp,
	modpe_id varchar(255),
	mod_dts timestamp,
    primary key(comment_id)
);

CREATE SEQUENCE BOARD_SEQ START WITH 2 INCREMENT BY 1;
CREATE SEQUENCE POST_SEQ START WITH 2 INCREMENT BY 1;
CREATE SEQUENCE COMMENT_SEQ START WITH 2 INCREMENT BY 1;


/*
CREATE TABLE BATCH_JOB_INSTANCE  (
    JOB_INSTANCE_ID BIGINT  PRIMARY KEY ,
    VERSION BIGINT,
    JOB_NAME VARCHAR(100) NOT NULL ,
    JOB_KEY VARCHAR(2500)
);
CREATE TABLE BATCH_JOB_EXECUTION  (
    JOB_EXECUTION_ID BIGINT  PRIMARY KEY ,
    VERSION BIGINT,
    JOB_INSTANCE_ID BIGINT NOT NULL,
    CREATE_TIME TIMESTAMP NOT NULL,
    START_TIME TIMESTAMP DEFAULT NULL,
    END_TIME TIMESTAMP DEFAULT NULL,
    STATUS VARCHAR(10),
    EXIT_CODE VARCHAR(20),
    EXIT_MESSAGE VARCHAR(2500),
    LAST_UPDATED TIMESTAMP,
    JOB_CONFIGURATION_LOCATION VARCHAR(2500) NULL,
    constraint JOB_INSTANCE_EXECUTION_FK foreign key (JOB_INSTANCE_ID)
    references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID)
);

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS  (
    JOB_EXECUTION_ID BIGINT NOT NULL ,
    TYPE_CD VARCHAR(6) NOT NULL ,
	KEY_NAME VARCHAR(100) NOT NULL ,
	STRING_VAL VARCHAR(250) ,
	DATE_VAL DATETIME DEFAULT NULL ,
	LONG_VAL BIGINT ,
	DOUBLE_VAL DOUBLE PRECISION ,
	IDENTIFYING CHAR(1) NOT NULL ,
	constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID)
	references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT  (
    JOB_EXECUTION_ID BIGINT PRIMARY KEY,
    SHORT_CONTEXT VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT CLOB,
    constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID)
    references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);

CREATE TABLE BATCH_STEP_EXECUTION  (
    STEP_EXECUTION_ID BIGINT  PRIMARY KEY ,
    VERSION BIGINT NOT NULL,
    STEP_NAME VARCHAR(100) NOT NULL,
    JOB_EXECUTION_ID BIGINT NOT NULL,
    START_TIME TIMESTAMP NOT NULL ,
    END_TIME TIMESTAMP DEFAULT NULL,
    STATUS VARCHAR(10),
    COMMIT_COUNT BIGINT ,
    READ_COUNT BIGINT ,
    FILTER_COUNT BIGINT ,
    WRITE_COUNT BIGINT ,
    READ_SKIP_COUNT BIGINT ,
    WRITE_SKIP_COUNT BIGINT ,
    PROCESS_SKIP_COUNT BIGINT ,
    ROLLBACK_COUNT BIGINT ,
    EXIT_CODE VARCHAR(20) ,
    EXIT_MESSAGE VARCHAR(2500) ,
    LAST_UPDATED TIMESTAMP,
    constraint JOB_EXECUTION_STEP_FK foreign key (JOB_EXECUTION_ID)
    references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID)
);

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT  (
    STEP_EXECUTION_ID BIGINT PRIMARY KEY,
    SHORT_CONTEXT VARCHAR(2500) NOT NULL,
    SERIALIZED_CONTEXT CLOB,
    constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID)
    references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID)
);
*/