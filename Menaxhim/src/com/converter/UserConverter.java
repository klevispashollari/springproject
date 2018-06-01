package com.converter;

import com.dtoModel.UserDto;
import com.entitete.Roli;
import com.entitete.User;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class UserConverter {

	public static User toUser(UserDto userDto) {
		User user = new User();
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		user.setId(userDto.getId());
		user.setFname(userDto.getName());
		user.setLname(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncryptor.encryptPassword(userDto
				.getPassword()));
		user.setStatus(1);
		Roli roli = new Roli();
		roli.setId(2);
		user.setRoli(roli);
		return user;

	}

	public static UserDto toUserDto(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getFname());
		userDto.setLastname(user.getLname());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setRoliId(user.getRoli().getId());
		userDto.setFullName(user.getFname() + " " + user.getLname());
		return userDto;
	}

	public static User toUpdateUser(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setFname(userDto.getName());
		user.setLname(userDto.getLastname());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		Roli roli = new Roli();
		roli.setId(userDto.getRoliId());
		user.setRoli(roli);
		return user;
	}

}
