package com.woowa.board.board;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="board")
@Getter
@NoArgsConstructor
public class Board {

	@Id
	@Column(name="board_id")
	private Long boardId;
	
	@Column(name="board_name")
	private String boardName;
	
	@Column(name="explanation")
	private String explanation;
	
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
	public Board(Long boardId, String boardName, String name, String explanation, String delYn, String regpeId, String modpeId) {
		this.boardId = boardId;
		this.boardName = boardName;
		this.explanation = explanation;
		this.delYn = delYn;
		this.regpeId = regpeId;;
		this.modpeId = modpeId;
	}
	
}
