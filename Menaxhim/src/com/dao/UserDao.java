package com.dao;

import java.util.ArrayList;

import com.entitete.User;

public interface UserDao {
	public boolean add(User user);

	public boolean edit(User user);

	public boolean delete(int userId);

	public User getUserFromId(int userId);

	public User getLoggedUser(String email);

	public ArrayList<User> getAllUser();

	public boolean changePassword(User user);
}
