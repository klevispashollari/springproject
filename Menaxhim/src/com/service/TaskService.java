package com.service;

import java.util.ArrayList;

import com.dtoModel.TaskDto;

public interface TaskService {
	public boolean add(TaskDto taskDto);

	public boolean edit(TaskDto taskDto);

	public boolean delete(ArrayList<TaskDto> taskId);

	public TaskDto getTask(int taskId);

	public ArrayList<TaskDto> getAllTask();

	public ArrayList<TaskDto> getTaskForUser(int userId);

	public ArrayList<TaskDto> filterTaskByName(String taskName, int userId);

}
