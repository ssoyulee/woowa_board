package com.woowa.board.comment;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class CommentId implements Serializable{

	@Column(name="post_id")
	private Long postId;
	
	@Column(name="comment_id")
	private Long commentId;
	
}
