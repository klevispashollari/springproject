package com.dao;

import java.util.ArrayList;

import com.entitete.Task;

public interface TaskDao {
	public boolean add(Task task);

	public boolean edit(Task task);

	public boolean delete(int taskId);

	public boolean deleteUserTasks(int userId);

	public Task getTask(int taskId);

	public ArrayList<Task> getAllTask();

	public ArrayList<Task> getTaskForUser(int userId);

	public ArrayList<Task> filterTaskByName(String taskName, int userId);

	public ArrayList<Task> filterTaskByGjendjeUser(String gjendje, int userId);

	public ArrayList<Task> filterTaskByGjendjeAdmin(String gjendje);
}
