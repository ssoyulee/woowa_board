package com.woowa.board.comment;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="comment")
@Getter
@NoArgsConstructor
public class Comment {

	@EmbeddedId
	private CommentId commentId;

	@Column(name="comment_content")
	private String commentContent;
	
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
	
	@Builder
	public Comment(CommentId commentId, String commentContent, String name, String explanation, String delYn, String regpeId, String modpeId) {
		this.commentId = commentId;
		this.commentContent = commentContent;
		this.delYn = delYn;
		this.regpeId = regpeId;;
		this.modpeId = modpeId;
	}
	
}
