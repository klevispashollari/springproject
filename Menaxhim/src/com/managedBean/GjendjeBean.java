package com.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;

import com.dtoModel.GjendjeDto;
import com.service.GjendjeService;
import com.utility.MessagesUtility;

@ManagedBean(name = "gjendjeBean")
@ViewScoped
public class GjendjeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static ResourceBundle bundle = ResourceBundle
			.getBundle("com.Messages");

	private ArrayList<GjendjeDto> gjendjeDtoList;
	private GjendjeDto gjendjeDto;

	@ManagedProperty(value = "#{gjendjeService}")
	private GjendjeService gjendjeService;

	@ManagedProperty(value = "#{taskBean}")
	private TaskBean taskBean;

	@PostConstruct
	public void init() {
		refreshBean();
	}

	public void refreshBean() {
		this.gjendjeDto = new GjendjeDto();
		this.gjendjeDtoList = new ArrayList<GjendjeDto>();
		this.gjendjeDtoList = gjendjeService.getAllGjendje();
	}

	public String updateGjendje(int taskId) {
		if (gjendjeService.updateGjendje(taskId, taskBean.getTaskDto()
				.getGjendjeId())) {
			MessagesUtility.addMessage(bundle.getString("TASK_STATUS_EDITED"));
		} else {
			MessagesUtility.addMessage(bundle
					.getString("TASK_STATUS_NOT_EDITED"));
		}
		refreshBean();
		return "";
	}

	public void updateGjendje(final AjaxBehaviorEvent event) {
		String output[] = taskBean.getTaskDto().getGjendjeAndTaskId()
				.split(" ");
		System.out.println(output[0]);
		System.out.println(output[1]);
		Integer taskId = Integer.valueOf(output[1]);
		Integer gjendjeId = Integer.valueOf(output[0]);

		System.out.println(taskBean.getTaskDto().getGjendjeId());

		if (gjendjeService.updateGjendje(taskId, gjendjeId)) {
			refreshBean();
			MessagesUtility.addMessage(bundle.getString("TASK_STATUS_EDITED"));
		} else {
			MessagesUtility.addMessage(bundle
					.getString("TASK_STATUS_NOT_EDITED"));
		}
		refreshBean();

	}

	public ArrayList<GjendjeDto> getGjendjeDtoList() {
		return gjendjeDtoList;
	}

	public void setGjendjeDtoList(ArrayList<GjendjeDto> gjendjeDtoList) {
		this.gjendjeDtoList = gjendjeDtoList;
	}

	public GjendjeDto getGjendjeDto() {
		return gjendjeDto;
	}

	public void setGjendjeDto(GjendjeDto gjendjeDto) {
		this.gjendjeDto = gjendjeDto;
	}

	public GjendjeService getGjendjeService() {
		return gjendjeService;
	}

	public void setGjendjeService(GjendjeService gjendjeService) {
		this.gjendjeService = gjendjeService;
	}

	public TaskBean getTaskBean() {
		return taskBean;
	}

	public void setTaskBean(TaskBean taskBean) {
		this.taskBean = taskBean;
	}

}
