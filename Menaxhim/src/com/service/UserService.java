package com.service;

import java.util.ArrayList;

import com.dtoModel.UserDto;

public interface UserService {
	public boolean add(UserDto userDto);

	public boolean edit(UserDto userDto);

	public boolean delete(int userId);

	public UserDto getUserFromId(int userId);

	public UserDto getLoggedUser(String email);

	public ArrayList<UserDto> getAllUser();

	public boolean changePassword(UserDto userDto);
}
