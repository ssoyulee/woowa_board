package com.woowa.board.board.dao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="board")
@SequenceGenerator(
        name="BOARD_SEQ_GENERATOR",
        sequenceName="BOARD_SEQ",
        initialValue=1,
        allocationSize=1
)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Board {

	@Id
	@Column(name="board_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE
    			,generator="BOARD_SEQ_GENERATOR"
    )
	private Long boardId;
	
	@Column(name="board_name",nullable = false)
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
	
	@Column(name="modpe_id",nullable = false)
	private String modpeId;
	
	@Column(name="mod_dts")
	@UpdateTimestamp
	private LocalDateTime modDts;
	
	@Builder
	public Board(String boardName, String name, String delYn, String explanation, String regpeId, String modpeId) {
		this.boardName = boardName;
		this.explanation = explanation;
		this.delYn = delYn;
		this.regpeId = regpeId;;
		this.modpeId = modpeId;
	}
	
}
