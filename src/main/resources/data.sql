insert into USER(user_id,password, name, email, regpe_id, reg_dts, modpe_id, mod_dts) values ('admin','admin','주인장','wj0612@nate.com','System',now(),'System',now());

insert into BOARD(board_id,board_name,explanation,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,'게시판1','찻반째게시판','N','System',now(),'System',now());

insert into POST(board_id,post_id,post_title,post_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,1,'게시글제목','게시글내용','N','System',now(),'System',now());

insert into COMMENT(post_id,comment_id,comment_content,del_yn, regpe_id, reg_dts, modpe_id, mod_dts) values (1,1,'댓글1','N','System',now(),'System',now());
