package com.bookbros.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "role", nullable = false)
	private String role;
	
	//OneToMany to purchase (same for wishlist)
	@OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Purchase.class )
    @JoinColumn(name="purchaser_id")
	List<Purchase> purchased;
	@OneToMany( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity=Wishlist.class )
    @JoinColumn(name="wisher_id")
	List<Wishlist> wishes;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Purchase> getPurchased() {
		return purchased;
	}
	public void setPurchased(List<Purchase> purchased) {
		this.purchased = purchased;
	}
	public List<Wishlist> getWishes() {
		return wishes;
	}
	public void setWishes(List<Wishlist> wishes) {
		this.wishes = wishes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((purchased == null) ? 0 : purchased.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((wishes == null) ? 0 : wishes.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (purchased == null) {
			if (other.purchased != null)
				return false;
		} else if (!purchased.equals(other.purchased))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (wishes == null) {
			if (other.wishes != null)
				return false;
		} else if (!wishes.equals(other.wishes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", purchased=" + purchased + ", wishes=" + wishes + "]";
	}
}
