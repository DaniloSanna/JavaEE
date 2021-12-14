package model;

import java.io.Serializable;

public class ModelLogin implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;
	private String pass;
	private String email;
	private String name;
	
	
	public ModelLogin() {
	}

	public ModelLogin(String login, String pass) {
		super();
		this.login = login;
		this.pass = pass;
	}
	public ModelLogin(Long id, String login, String pass, String email, String name) {
		super();
		this.id = id;
		this.login = login;
		this.pass = pass;
		this.email = email;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "ModelLogin [id=" + id + ", login=" + login + ", pass=" + pass + ", email=" + email + ", name=" + name
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPass() {
		return pass;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	
}
