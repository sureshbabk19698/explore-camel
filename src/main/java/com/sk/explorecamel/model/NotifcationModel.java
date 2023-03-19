package com.sk.explorecamel.model;

import java.util.Date;

import lombok.Data;

@Data
public class NotifcationModel {

	private String headline;
	private String content;
	private int feedNo;
	private Date date;
}
