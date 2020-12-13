insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('admin','admin','admin','wj0612@nate.com', 'ADMIN', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user1','1','username1','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user2','1','username2','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user3','1','username3','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user4','1','username4','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user5','1','username5','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());

insert into BOARD(board_id,board_name,explanation,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,'BOARD1','BOARD EXPLANMATION1','N','admin',now(),'admin',now());
insert into BOARD(board_id,board_name,explanation,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,'BOARD2','BOARD EXPLANMATION2','N','admin',now(),'admin',now());

insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,1,'BOARD1_POST1','BOARD1_POST1_USER1','N','user1',now(),'user1',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,2,'BOARD1_POST2','BOARD1_POST2_USER1','N','user1',now(),'user1',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,3,'BOARD1_POST3','BOARD1_POST3_USER1','N','user1',now(),'user1',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,4,'BOARD2_POST4','BOARD2_POST4_USER1','N','user1',now(),'user1',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,5,'BOARD2_POST5','BOARD2_POST5_USER2','N','user2',now(),'user2',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,6,'BOARD2_POST6','BOARD2_POST6_USER2','N','user2',now(),'user2',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,7,'BOARD1_POST7','BOARD1_POST7_USER3','N','user3',now(),'user3',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,8,'BOARD2_POST8','BOARD2_POST8_USER3','N','user3',now(),'user3',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,9,'BOARD2_POST9','BOARD2_POST9_USER4','N','user4',now(),'user4',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,10,'BOARD2_POST10','BOARD2_POST10_USER4','N','user4',now(),'user4',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,11,'BOARD2_POST11','BOARD2_POST11_USER5','N','user5',now(),'user5',now());

insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (11,1,'COMMENT1','N','user1',now(),'user1',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (11,2,'COMMENT2','N','user2',now(),'user2',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (11,3,'COMMENT3','N','user3',now(),'user3',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,4,'COMMENT4','N','user4',now(),'user4',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,5,'COMMENT5','N','user5',now(),'user5',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,6,'COMMENT6','N','user1',now(),'user1',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,7,'COMMENT7','N','user2',now(),'user2',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,8,'COMMENT8','N','user3',now(),'user3',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,9,'COMMENT9','N','user4',now(),'user4',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,10,'COMMENT10','N','user5',now(),'user5',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,11,'COMMENT11','N','user1',now(),'user1',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,12,'COMMENT12','N','user2',now(),'user2',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,13,'COMMENT13','N','user3',now(),'user3',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,14,'COMMENT14','N','user4',now(),'user4',now());

