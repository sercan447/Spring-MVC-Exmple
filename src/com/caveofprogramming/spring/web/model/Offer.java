package com.caveofprogramming.spring.web.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="offers")
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	/*@Size(min=3,max=10,message="ismi 3 ile 10 araasýnda giriniz.")
	private String name;
	
	@NotNull(message="Mail girmek zorunludur.")
	@Pattern(regexp=".*\\@.*\\..*",message ="Mailinizi Düzgün giriniz.")
	private String email;
	*/
	@Size(min=10,max=50,message="Yorum sýnýrýný aþmýþsýnýz veya aþmamýþsýnýz.")
	private String text;
		
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="username")
	private User user;
	
	public Offer() {
		this.user = new User();
	}
	

	public Offer(int id,
			@Size(min = 10, max = 50, message = "Yorum sýnýrýný aþmýþsýnýz veya aþmamýþsýnýz.") String text,
			User user) {
		
		this.id = id;
		this.text = text;
		this.user = user;
	}



	public Offer(@Size(min = 10, max = 50, message = "Yorum sýnýrýný aþmýþsýnýz veya aþmamýþsýnýz.") String text,
			User user) {
		
		this.text = text;
		this.user = user;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", text=" + text + ", user=" + user + "]";
	}

	/*
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	 */

	

	
}//CLASS
