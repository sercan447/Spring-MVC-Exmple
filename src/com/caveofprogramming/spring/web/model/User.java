package com.caveofprogramming.spring.web.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="users")
public class User implements Serializable {
		
	private static final long serialVersionUID = 1L;

	@Id
	//private int id;
	//@NotNull
	//@Size(min=8,max=15)
	//@Pattern(regexp="^\\w{8,}$")
	private String username;
	
	//@NotNull
	//@Pattern(regexp="^\\S+$")
	//@Size(min=8,max=15)
	private String password;
	
	
	//@NotNull
	//@Pattern(regexp="[A-Za-z]+")
	//@Email(message="This does not appear to be a valid email adress" , regexp="[A-Z]+")
	private String email;
	private boolean enabled = true;
	private String name;
	private String authority;
	
	
	public User() {}
	
	public User(String username, String name,String password, String email, boolean enabled, String authority) {
		
		this.username = username;
		this.name = name;
		this.password = password;
		this.email = email;
		this.enabled = enabled;
		this.authority = authority;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", enabled=" + enabled
				+ ", name=" + name + ", authority=" + authority + "]";
	}

	

	
	
	
	
	
	
}
