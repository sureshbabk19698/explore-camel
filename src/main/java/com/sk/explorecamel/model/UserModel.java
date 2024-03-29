package com.sk.explorecamel.model;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.sk.explorecamel.enums.Gender;

import lombok.Data;

@Data
public class UserModel {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String dateOfBirth;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private Date lastLoginTs;
}
