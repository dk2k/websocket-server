package ru.outofrange.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
	
	private static final long serialVersionUID = 993269108415593695L;

	private Long id;

	private String username;
	
	private String password;
	
	private Set<Token> tokens = new HashSet<>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<Token> getTokens() {
		return tokens;
	}
	public void setTokens(Set<Token> tokens) {
		this.tokens = tokens;
	}
	
}
