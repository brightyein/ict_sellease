package com.ict.carrot.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@Column(unique = true)
	private String username;

	@NotBlank
	private String password;

	@Email(message="이메일 형식을 확인해주세요.")
	private String email;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

}
