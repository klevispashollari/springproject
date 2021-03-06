package com.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.dtoModel.UserDto;
import com.service.UserService;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	private UserDto userDto;

	public void logOut() {
		userDto = null;
	}

	// GETTERS AND SETTERS
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}
}
