package com.converter;

import com.dtoModel.TaskDto;
import com.entitete.Gjendje;
import com.entitete.Task;
import com.entitete.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskConverter {

	public static Task toTask(TaskDto taskDto) {
		Task task = new Task();
		task.setEmer(taskDto.getEmer());
		task.setPershkrim(taskDto.getPershkrim());
		task.setDateNisje(toSqlDate(taskDto.getDateNisje()));
		task.setDatePerfundimi(toSqlDate(taskDto.getDatePerfundimi()));
		task.setStatus(1);
		User user = new User();
		user.setId(taskDto.getUserId());
		Gjendje gjendje = new Gjendje();
		gjendje.setId(1);
		task.setUser(user);
		task.setGjendje(gjendje);

		return task;
	}

	public static Task toTaskUpdate(TaskDto taskDto) {
		Task task = new Task();
		task.setId(taskDto.getId());
		task.setEmer(taskDto.getEmer());
		task.setPershkrim(taskDto.getPershkrim());
		task.setDateNisje(toSqlDate(taskDto.getDateNisje()));
		task.setDatePerfundimi(toSqlDate(taskDto.getDatePerfundimi()));
		task.setStatus(1);
		User user = new User();
		user.setId(taskDto.getUserId());

		// nese eshte admin
		// if (user.getStatus() == 1) {
		Gjendje gjendje = new Gjendje();
		gjendje.setId(taskDto.getGjendjeId());
		task.setGjendje(gjendje);
		// u be
		// }
		task.setUser(user);

		return task;
	}

	public static TaskDto toTaskDto(Task task) {
		TaskDto taskDto = new TaskDto();
		taskDto.setId(task.getId());
		taskDto.setEmer(task.getEmer());
		taskDto.setPershkrim(task.getPershkrim());
		taskDto.setDateNisje(toUtilDate(task.getDateNisje()));
		taskDto.setDatePerfundimi(toUtilDate(task.getDatePerfundimi()));
		taskDto.setUserId(task.getUser().getId());
		taskDto.setEmerPunonjes(task.getUser().getFname());
		taskDto.setMbiemerPunonjes(task.getUser().getLname());
		taskDto.setGjendjeId(task.getGjendje().getId());
		return taskDto;

	}

	private static java.sql.Timestamp toSqlDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		try {

			Date dateFromString = format.parse(date);
			System.out.println(date);
			System.out.println(dateFromString);
			return new java.sql.Timestamp(dateFromString.getTime());
		} catch (Exception e) {
			return null;
		}

	}

	private static String toUtilDate(java.sql.Timestamp date) {
		SimpleDateFormat dateformat2 = new SimpleDateFormat(
				"M/dd/yyyy hh:mm:ss");

		try {

			return dateformat2.format(date);

		} catch (Exception e) {

			return null;

		}

	}

}
