package com.woowa.board.post.dao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.woowa.board.board.dao.Board;
import com.woowa.board.user.dao.UserAccount;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="post")
@SequenceGenerator(
        name="POST_SEQ_GENERATOR",
        sequenceName="POST_SEQ",
        initialValue=1,
        allocationSize=1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Post {

	@Id
	@Column(name="post_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE
					,generator="POST_SEQ_GENERATOR"
    )
	private Long postId;
	
	@Column(name="board_id")
	private Long boardId;
	
	@Column(name="post_title")
	private String postTitle;
	
	@Column(name="post_content")
	private String postContent;
	
	@Column(name="del_yn")
	private String delYn;	
	
	@Column(name="regpe_id")
	private String regpeId;
	
	@Column(name="reg_dts")
	@CreationTimestamp
	private LocalDateTime regDts;
	
	@Column(name="modpe_id")
	private String modpeId;
	
	@Column(name="mod_dts")
	@UpdateTimestamp
	private LocalDateTime modDts;

	@ManyToOne
	@JoinColumn(name="regpe_id", referencedColumnName="user_id", insertable=false, updatable=false)
	private UserAccount user;

	@ManyToOne
	@JoinColumn(name="board_id", referencedColumnName="board_id", insertable=false, updatable=false)
	private Board board;
	
	@Builder
	public Post(Long boardId, String postTitle, String postContent, String explanation, String delYn, String regpeId, String modpeId) {
		this.boardId = boardId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.delYn = delYn;
		this.regpeId = regpeId;;
		this.modpeId = modpeId;
	}
	
}
