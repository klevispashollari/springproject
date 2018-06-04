package com.service.impl;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.converter.TaskConverter;
import com.dao.TaskDao;
import com.dtoModel.TaskDto;
import com.entitete.Task;
import com.service.TaskService;

@Service("taskService")
public class TaskServiceImpl implements TaskService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private TaskDao taskDao;

	@Transactional
	public boolean add(TaskDto taskDto) {
		return taskDao.add(TaskConverter.toTask(taskDto));
	}

	@Transactional
	public boolean edit(TaskDto taskDto) {
		return taskDao.edit(TaskConverter.toTaskUpdate(taskDto));
	}

	@Transactional
	public boolean delete(int taskId) {
		return taskDao.delete(taskId);
	}

	@Transactional
	public boolean delete(ArrayList<TaskDto> tasksId) {
		boolean control = false;
		for (int i = 0; i < tasksId.size(); i++) {
			control = taskDao.delete(tasksId.get(i).getId());
		}
		if (control) {
			return true;
		} else {
			return false;
		}
	}

	@Transactional
	public TaskDto getTask(int taskId) {
		return TaskConverter.toTaskDto(taskDao.getTask(taskId));
	}

	@Transactional
	public ArrayList<TaskDto> getAllTask() {
		ArrayList<TaskDto> taskDtoList = new ArrayList<>();
		ArrayList<Task> entityTask = taskDao.getAllTask();
		if (entityTask != null) {

			for (int i = 0; i < entityTask.size(); i++) {
				taskDtoList.add(TaskConverter.toTaskDto(entityTask.get(i)));
			}
		}
		return taskDtoList;
	}

	@Transactional
	public ArrayList<TaskDto> getTaskForUser(int userId) {
		ArrayList<TaskDto> taskDtoForUserList = new ArrayList<>();
		ArrayList<Task> entityTaskForUser = taskDao.getTaskForUser(userId);
		for (int i = 0; i < entityTaskForUser.size(); i++) {
			taskDtoForUserList.add(TaskConverter.toTaskDto(entityTaskForUser
					.get(i)));
		}
		return taskDtoForUserList;
	}

	@Transactional
	public ArrayList<TaskDto> filterTaskByName(String taskName, int userId) {
		ArrayList<TaskDto> taskDtoForUserListFiltered = new ArrayList<>();
		ArrayList<Task> entityTaskForUserListFiltered = taskDao
				.filterTaskByName(taskName, userId);
		if (entityTaskForUserListFiltered != null) {
			for (int i = 0; i < entityTaskForUserListFiltered.size(); i++) {
				taskDtoForUserListFiltered.add(TaskConverter
						.toTaskDto(entityTaskForUserListFiltered.get(i)));
			}
		}

		return taskDtoForUserListFiltered;
	}

}
