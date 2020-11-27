package com.woowa.board.user.dao;

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

@Entity(name = "user")
@SequenceGenerator(
        name="USER_SEQ_GENERATOR",
        sequenceName="USER_SEQ",
        initialValue=1,
        allocationSize=1
)
public class User {
	
	@Id
	@Column(name="user_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE
		,generator="USER_SEQ_GENERATOR"
	)
	private String userId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="ranking")
	private Long ranking;
	
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
	public User(String userId, String password, String name, String email, Long ranking, String delYn, String regpeId, String modpeId) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.ranking = ranking;
		this.delYn = delYn;
		this.regpeId = regpeId;;
		this.modpeId = modpeId;
	}
	
}
