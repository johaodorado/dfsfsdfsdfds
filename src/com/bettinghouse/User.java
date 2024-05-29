package com.bettinghouse;

import java.util.Objects;

public class User {
	
    private String nickname;
    private String password;
    
	public User(String nickname, String password) {
		super();
		this.nickname = nickname;
		this.password = password;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nickname, password);
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
		return Objects.equals(nickname, other.nickname) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		return "nickname = " + nickname + ", password = " + password;
	}
	
}