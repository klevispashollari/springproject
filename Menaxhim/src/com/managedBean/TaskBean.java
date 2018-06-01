package com.managedBean;

import java.io.Serializable;
import java.text.ParseException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import org.apache.log4j.Logger;

import com.dtoModel.TaskDto;
import com.service.TaskService;
import com.service.UserService;
import com.utility.MessagesUtility;

@ManagedBean
@ViewScoped
public class TaskBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static ResourceBundle bundle = ResourceBundle
			.getBundle("com.Messages");
	private TaskDto taskDto;
	private ArrayList<TaskDto> taskDtoList;
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
			MessagesUtility.addMessage(bundle.getString("SEARCH_FAIL"));

		} else {
			MessagesUtility.addMessage(bundle.getString("SEARCH_SUCCESS"));
		}
		return taskDtoList;
	}

	public String addTask() throws ParseException {
		Logger log = Logger.getLogger(TaskBean.class);
		log.info("adding ");
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dataSot = new Date();
		if (validateDate(format.parse(taskDto.getDateNisje()),
				format.parse(taskDto.getDatePerfundimi()), dataSot)) {
			if (taskService.add(taskDto)) {
				log.info("sucess");
				refreshBean();
				MessagesUtility.addMessage(bundle.getString("TASK_ADDED"));
			}
		} else {
			MessagesUtility.addMessage(bundle.getString("TASK_NOT_ADDED"));
		}
		return null;
	}

	public String deleteTask(int taskId) {
		if (taskService.delete(taskId)) {
			MessagesUtility.addMessage(bundle.getString("TASK_DELETED"));
		} else {
			MessagesUtility.addMessage(bundle.getString("TASK_NOT_DELETED"));
		}
		return null;
	}

	public String editTask() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date dataSot = new Date();
		
		if (validateDate(format.parse(taskDto.getDateNisje()),
				format.parse(taskDto.getDatePerfundimi()), dataSot)) {
			if (taskService.edit(taskDto)) {
				refreshBean();
				MessagesUtility.addMessage(bundle.getString("TASK_EDITED"));
			}
		} else {
			MessagesUtility.addMessage(bundle.getString("TASK_NOT_EDITED"));
		}
		return null;
	}

	private boolean validateDate(Date dateNisje, Date datePerfundimi,
			Date dataSot) {
		if (dateNisje.compareTo(dataSot) < 0) {
			MessagesUtility
					.addMessage(bundle.getString("TASK_STARTDATE_ERROR"));
			return false;
		} else {
			if (datePerfundimi.compareTo(dateNisje) < 0) {
				MessagesUtility.addMessage(bundle
						.getString("TASK_ENDDATE_ERROR"));
				return false;
			} else {
				return true;
			}
		}
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

}
