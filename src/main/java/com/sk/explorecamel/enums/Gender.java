package com.sk.explorecamel.enums;

import lombok.Getter;

@Getter
public enum Gender {

	Male("M"), Female("F"), Agender("A");

	private final String genderCode;

	private Gender(String genderCode) {
		this.genderCode = genderCode;
	}

	public static Gender findByCode(String genderCode) {
		for (Gender gender : Gender.values()) {
			if (gender.genderCode.equalsIgnoreCase(genderCode)) {
				return gender;
			}
		}
		return null;
	}

}
