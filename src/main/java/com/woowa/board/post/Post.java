package com.woowa.board.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name="post")
@Getter
@ToString
@NoArgsConstructor
public class Post {

	@EmbeddedId
	private PostId postId;
	
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
	
	@Builder
	public Post(PostId postId, String postTitle, String postContent, String explanation, String delYn, String regpeId, String modpeId) {
		this.postId = postId;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.delYn = delYn;
		this.regpeId = regpeId;;
		this.modpeId = modpeId;
	}
	
}
