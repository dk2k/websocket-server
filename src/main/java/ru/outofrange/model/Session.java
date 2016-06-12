package ru.outofrange.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Session {
	
	private Long id;
	
	private String sessionId;
	private Date date;
	
	private Set<JsonMessage> messages = new HashSet<JsonMessage>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<JsonMessage> getMessages() {
		return messages;
	}

	public void setMessages(Set<JsonMessage> messages) {
		this.messages = messages;
	}

}
