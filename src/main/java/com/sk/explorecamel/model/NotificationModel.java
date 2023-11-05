package com.sk.explorecamel.model;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationModel {

	private Long feedId;
	private String headline;
	private String content;
	private Date lastEditedDate;
}
