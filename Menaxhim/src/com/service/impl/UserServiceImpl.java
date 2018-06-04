package com.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.converter.UserConverter;
import com.dao.TaskDao;
import com.dao.UserDao;
import com.dtoModel.UserDto;
import com.entitete.User;
import com.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskDao taskDao;

	@Transactional
	public boolean add(UserDto userDto) {
		return userDao.add(UserConverter.toUser(userDto));
	}

	@Transactional
	public boolean edit(UserDto userDto) {
		return userDao.edit(UserConverter.toUser(userDto));
	}

	@Transactional
	public boolean delete(int userId) {
		if (userDao.delete(userId) && taskDao.deleteUserTasks(userId))
			return true;
		return false;
	}

	@Transactional
	public ArrayList<UserDto> getAllUser() {
		ArrayList<UserDto> userDtoList = new ArrayList<>();
		ArrayList<User> userList = userDao.getAllUser();
		for (int i = 0; i < userList.size(); i++) {
			userDtoList.add(UserConverter.toUserDto(userList.get(i)));
		}
		return userDtoList;
	}

	@Transactional
	public UserDto getLoggedUser(String email) {
		User user = userDao.getLoggedUser(email);
		if (user != null) {
			return UserConverter.toUserDto(user);
		} else {
			return null;
		}
	}

	@Transactional
	public UserDto getUserFromId(int userId) {
		return UserConverter.toUserDto(userDao.getUserFromId(userId));
	}

	@Transactional
	public boolean changePassword(UserDto userDto) {
		return userDao.changePassword(UserConverter.toUpdateUser(userDto));
	}

}
