package com.managedBean;

import java.io.Serializable;
import java.text.ParseException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import com.dtoModel.TaskDto;
import com.service.TaskService;
import com.service.UserService;
import com.utility.MessagesUtility;
import com.utility.Validate;

@ManagedBean(name = "taskBean")
@ViewScoped
public class TaskBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TaskDto taskDto;
	private ArrayList<TaskDto> taskDtoList;
	private ArrayList<TaskDto> selectedTask;

	@ManagedProperty(value = "#{taskService}")
	private TaskService taskService;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@PostConstruct
	public void init() {
		refreshBean();
	}

	public void refreshBean() {
		taskDto = new TaskDto();
		taskDtoList = new ArrayList<TaskDto>();
		this.taskDtoList = getTasks();
	}

	public ArrayList<TaskDto> getTasks() {
		switch (userBean.getUserDto().getRoliId()) {
		case 1:
			return taskService.getAllTask();
		case 2:
			return taskService.getTaskForUser(userBean.getUserDto().getId());
		}
		return null;
	}

	public ArrayList<TaskDto> filterTaskByName() {

		if (userBean.getUserDto().getRoliId() == 1) {
			this.taskDtoList = taskService.filterTaskByName(taskDto.getEmer(),
					taskDto.getUserId());
		} else {
			this.taskDtoList = taskService.filterTaskByName(taskDto.getEmer(),
					userBean.getUserDto().getId());
		}
		if (taskDtoList.size() == 0) {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("SEARCH_FAIL"));

		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("SEARCH_SUCCESS"));
		}
		return taskDtoList;
	}

	public ArrayList<TaskDto> filterTaskByGjendje(String gjendje) {
		if (userBean.getUserDto().getRoliId() == 1) {
			this.taskDtoList = taskService.filterTaskByGjendje(gjendje, null);
		} else {
			this.taskDtoList = taskService.filterTaskByGjendje(gjendje,
					userBean.getUserDto().getId());
		}
		if (taskDtoList.size() == 0) {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("SEARCH_GJENDJE_FAIL"));
		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("SEARCH_SUCCESS"));
		}

		return taskDtoList;
	}

	public String addTask() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dataSot = new Date();
		if (Validate.validateDate(format.parse(taskDto.getDateNisje()),
				format.parse(taskDto.getDatePerfundimi()), dataSot)) {
			if (taskService.add(taskDto)) {
				refreshBean();
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("TASK_ADDED"));
			}
		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("TASK_NOT_ADDED"));
		}
		return null;
	}

	public String deleteSelectedTasks() {

		if (taskService.delete(getSelectedTask())) {
			if (getSelectedTask().size() == 1) {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("TASK_DELETED"));
			} else {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("TASKS_DELETED"));
			}
		} else {
			if (getSelectedTask().size() == 0) {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("TASK_NOT_SELECTED"));
			} else {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("TASK_NOT_DELETED"));
			}

		}

		return null;
	}

	public String editTask() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dataSot = new Date();
		if (Validate.validateDate(format.parse(taskDto.getDateNisje()),
				format.parse(taskDto.getDatePerfundimi()), dataSot)) {
			if (taskService.edit(taskDto)) {
				refreshBean();
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("TASK_EDITED"));
			}
		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("TASK_NOT_EDITED"));
		}

		return null;
	}

	// GETTERS AND SETTERS
	public void setTaskDtoList(ArrayList<TaskDto> taskDtoList) {
		this.taskDtoList = taskDtoList;
	}

	public TaskService getTaskService() {
		return taskService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}

	public TaskDto getTaskDto() {
		return taskDto;
	}

	public void setTaskDto(TaskDto taskDto) {
		this.taskDto = taskDto;
	}

	public ArrayList<TaskDto> getTaskDtoList() {
		return taskDtoList;
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

	public ArrayList<TaskDto> getSelectedTask() {
		return selectedTask;
	}

	public void setSelectedTask(ArrayList<TaskDto> selectedTask) {
		this.selectedTask = selectedTask;
	}

}
