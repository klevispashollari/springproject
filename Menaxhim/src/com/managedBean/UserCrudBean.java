package com.managedBean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.dtoModel.UserDto;
import com.service.UserService;
import com.utility.MessagesUtility;
import com.utility.Validate;

@ManagedBean(name = "userCrudBean")
@ViewScoped
public class UserCrudBean implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private UserDto userDto;
	private ArrayList<UserDto> userDtoList;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	@PostConstruct
	public void init() {
		refreshBean();
	}

	public void refreshBean() {
		this.userDto = new UserDto();
		this.userDtoList = new ArrayList<UserDto>();
		this.userDtoList = userService.getAllUser();
	}

	public String addUser() {
		if (!userExists()) {
			if (userService.add(userDto)) {
				refreshBean();
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("USER_ADDED"));
			} else {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("USER_NOT_ADDED"));
			}
		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("EMAIL_EXISTS"));
		}
		return null;
	}

	public String deleteUser(int userId) {
		if (userService.delete(userId)) {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("USER_DELETED"));
		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("USER_NOT_DELETED"));
		}
		return null;
	}

	public String editUser() {
		UserDto user = userService.getUserFromId(userDto.getId());
		if (!userExists()
				|| (userExists() && user.getEmail().equals(userDto.getEmail()))) {

			if (userService.edit(userDto)) {
				refreshBean();
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("USER_EDITED"));
			} else {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("USER_NOT_EDITED"));
			}
		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("EMAIL_EXISTS"));
		}
		return null;
	}

	private boolean userExists() {
		if (userService.getLoggedUser(this.userDto.getEmail()) != null)
			return true;
		return false;
	}

	public String changePassword() {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();

		if (Validate.changePassword(userDto.getPassword(),
				userDto.getNewPassword(), userDto.getConfirmPassword(),
				userBean.getUserDto().getPassword())) {
			userBean.getUserDto()
					.setPassword(
							passwordEncryptor.encryptPassword(userDto
									.getNewPassword()));

			if (userService.changePassword(userBean.getUserDto())) {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("PASSWORD_CHANGED"));
			} else {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("PASSWORD_NOT_CHANGED"));
			}
		}
		return null;
	}

	// GETTERS AND SETTERS
	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public ArrayList<UserDto> getUserDtoList() {
		return userDtoList;
	}

	public void setUserDtoList(ArrayList<UserDto> userDtoList) {
		this.userDtoList = userDtoList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
