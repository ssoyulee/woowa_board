package com.woowa.board.user.dao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.woowa.board.user.code.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "user")
public class User {
	
	@Id
	@Column(name="user_id")
	private String userId;
	
	@Column(name="password")
	private String password;
	
	@Column(name="name")
	private String name;
	
	@Email
	@Column(name="email")
	private String email;
	
	@Column(name="ranking")
	private Long ranking;

	@Column(name="score")
	private Long score;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private UserRole role;	
	
	@Column(name="session_id")
	private String sessionId;	
	
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
	public User(String userId, String password, String name, String email, Long ranking, Long score, UserRole role, String delYn, String regpeId, String modpeId) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
		this.ranking = ranking;
		this.score = score;
		this.role = role;
		this.delYn = delYn;
		this.regpeId = regpeId;;
		this.modpeId = modpeId;
	}
	
}
