package com.dtoModel;

import java.io.Serializable;

public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int roliId;
	private String name;
	private String lastname;
	private String email;
	private String password;
	private String newPassword;
	private String confirmPassword;
	private String fullName;
	private boolean menaxher;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoliId() {
		return roliId;
	}

	public void setRoliId(int roliId) {
		this.roliId = roliId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public boolean getMenaxher() {
		if (roliId == 1) {
			menaxher = true;
		} else {
			menaxher = false;
		}
		return menaxher;
	}

	public void setMenaxher(boolean menaxher) {
		this.menaxher = menaxher;
	}

}
