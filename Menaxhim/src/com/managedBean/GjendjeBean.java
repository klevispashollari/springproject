package com.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import com.dtoModel.GjendjeDto;
import com.service.GjendjeService;
import com.utility.MessagesUtility;

@ManagedBean
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
