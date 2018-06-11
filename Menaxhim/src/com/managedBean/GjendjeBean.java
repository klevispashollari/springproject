package com.managedBean;

import java.util.ArrayList;

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
public class GjendjeBean {

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
		this.gjendjeDtoList = gjendjeService.getAllGjendje();
	}

	public void updateGjendje(final AjaxBehaviorEvent event) {
		String output[] = taskBean.getTaskDto().getGjendjeAndTaskId()
				.split(" ");
		Integer taskId = Integer.valueOf(output[1]);
		Integer gjendjeId = Integer.valueOf(output[0]);
		Integer oldGjendjeId = Integer.valueOf(output[2]);

		if (gjendjeId > oldGjendjeId) {
			if (gjendjeService.updateGjendje(taskId, gjendjeId)) {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("TASK_STATUS_EDITED"));
			} else {
				MessagesUtility.addMessage(MessagesUtility.bundle
						.getString("TASK_STATUS_NOT_EDITED"));
			}
		} else if (gjendjeId == oldGjendjeId) {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("TASK_STATUS_SAME"));
		} else {
			MessagesUtility.addMessage(MessagesUtility.bundle
					.getString("TASK_STATUS_OLD"));
		}

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
