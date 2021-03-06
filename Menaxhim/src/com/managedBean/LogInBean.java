package com.managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.dtoModel.UserDto;
import com.service.UserService;
import com.utility.MessagesUtility;

@ManagedBean(name = "logInBean")
@RequestScoped
public class LogInBean {

	private String email;
	private String password;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public String logIn() {
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		UserDto userDto = userService.getLoggedUser(email);
		if (userDto != null) {
			if (passwordEncryptor.checkPassword(password,

			userDto.getPassword())) {
				userBean.setUserDto(userDto);
				return "home.xhtml?faces-redirect=true";
			} else {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("INCORRECT_PASSWORD"));
				return null;
			}
		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("INCORRECT_EMAIL"));
			return null;
		}

	}

	public String logOut() {
		userBean.logOut();
		return "login.xhtml?faces-redirect=true";
	}

	// GETTERS AND SETTERS
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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
