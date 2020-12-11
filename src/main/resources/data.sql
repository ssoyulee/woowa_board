insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('admin','admin','주인장','wj0612@nate.com', 'ADMIN', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user1','1','사용자1','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user2','1','사용자2','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user3','1','사용자3','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user4','1','사용자4','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());
insert into USER(user_id,password, name, email, role, ranking, score, del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values ('user5','1','사용자5','wj0612@nate.com', 'MEMBER', 0, 0, 'N', 'System',now(),'System',now());

insert into BOARD(board_id,board_name,explanation,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,'게시판_하나','첫번째게시판','N','admin',now(),'admin',now());
insert into BOARD(board_id,board_name,explanation,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,'게시판_둘','첫번째게시판','N','admin',now(),'admin',now());

insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,1,'게시판_하나_게시글_하나','게시판_하나_게시물_하나_사용자1','N','user1',now(),'user1',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,2,'게시판_하나_게시글_둘','게시판_하나_게시물_둘_사용자1','N','user1',now(),'user1',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,3,'게시판_하나_게시글_셋','게시판_하나_게시물_셋_사용자1','N','user1',now(),'user1',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,4,'게시판_둘_게시글_넷','게시판_둘_게시글_넷_사용자1','N','user1',now(),'user1',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,5,'게시판_둘_게시글_다섯','게시판_둘_게시글_다섯_사용자2','N','user2',now(),'user2',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,6,'게시판_둘_게시글_여섯','게시판_둘_게시글_여섯_사용자2','N','user2',now(),'user2',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,7,'게시판_하나_게시글_일곱','게시판_하나_게시글_일곱_사용자3','N','user3',now(),'user3',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,8,'게시판_둘_게시글_여덟','게시판_둘_게시글_여덟_사용자3','N','user3',now(),'user3',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,9,'게시판_둘_게시글_아홉','게시판_둘_게시글_아홉_사용자4','N','user4',now(),'user4',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,10,'게시판_둘_게시글_열','게시판_둘_게시글_열_사용자4','N','user4',now(),'user4',now());
insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (2,11,'게시판_둘_게시글_열하나','게시판_둘_게시글_열하나_사용자5','N','user5',now(),'user5',now());

insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (11,1,'게시글_열하나_댓글1','N','user1',now(),'user1',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (11,2,'게시글_열하나_댓글2','N','user2',now(),'user2',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (11,3,'게시글_열하나_댓글3','N','user3',now(),'user3',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,4,'게시글_열_댓글4','N','user4',now(),'user4',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,5,'게시글_열_댓글5','N','user5',now(),'user5',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,6,'게시글_열_댓글6','N','user1',now(),'user1',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,7,'게시글_열_댓글7','N','user2',now(),'user2',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,8,'게시글_열_댓글8','N','user3',now(),'user3',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (10,9,'게시글_열_댓글9','N','user4',now(),'user4',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,10,'게시글_열_댓글10','N','user5',now(),'user5',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,11,'게시글_열_댓글11','N','user1',now(),'user1',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,12,'게시글_열_댓글12','N','user2',now(),'user2',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,13,'게시글_열_댓글13','N','user3',now(),'user3',now());
insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (7,14,'게시글_열_댓글14','N','user4',now(),'user4',now());

