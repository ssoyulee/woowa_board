package com.woowa.board.comment.dao;

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

import com.woowa.board.post.dao.Post;
import com.woowa.board.user.dao.UserAccount;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="comment")
@SequenceGenerator(
        name="COMMENT_SEQ_GENERATOR",
        sequenceName="COMMENT_SEQ",
        initialValue=1,
        allocationSize=1
)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Comment {

	@Id
	@Column(name="comment_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE
    			,generator="COMMENT_SEQ_GENERATOR"
    )
	private Long commentId;
	
	@Column(name="post_id")
	private Long postId;

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

	@ManyToOne
	@JoinColumn(name="regpe_id", referencedColumnName="user_id", insertable=false, updatable=false)
	private UserAccount user;
	
	@ManyToOne
	@JoinColumn(name="post_id", referencedColumnName="post_id", insertable=false, updatable=false)
	private Post post;
	
	
	@Builder
	public Comment(Long commentId, Long postId, String commentContent, String delYn, String regpeId, String modpeId) {
		this.commentId = commentId;
		this.postId = postId;
		this.commentContent = commentContent;
		this.delYn = delYn;
		this.regpeId = regpeId;;
		this.modpeId = modpeId;
	}
	
}
