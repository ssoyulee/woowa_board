package com.woowa.board.post;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class PostId implements Serializable{
	
	private static final long serialVersionUID = 7121342962812604694L;

	@Column(name="board_id")
	private Long boardId;
	
	@Column(name="post_id")
	private Long postId;

}
