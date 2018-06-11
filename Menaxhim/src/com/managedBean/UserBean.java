package com.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.dtoModel.UserDto;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	private UserDto userDto;

	public void logOut() {
		userDto = null;
	}

	// GETTERS AND SETTERS

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
}
