package com.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.TaskDao;
import com.entitete.Task;

@Repository(value = "TaskDao")
@Scope("singleton")
class TaskDaoImpl implements TaskDao {

	private static Logger log = Logger.getLogger(TaskDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public boolean add(Task task) {
		try {
			log.info("Adding the task!");
			entityManager.persist(task);
			log.info("Task added!");
			return true;
		} catch (Exception e) {
			log.error("Task failed to add! Message " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean edit(Task task) {
		try {
			log.info("Editing the task!");
			entityManager.merge(task);
			log.info("Task edited!");
			return true;
		} catch (Exception e) {
			log.error("Task failed to edit! Message " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete(int taskId) {
		try {
			log.info("Deleting the task!");
			entityManager
					.createQuery("update Task set status=:status where id=:id")
					.setParameter("status", 0).setParameter("id", taskId)
					.executeUpdate();
			log.info("Task deleted!");
			return true;
		} catch (Exception e) {
			log.error("Task failed to delete! Message " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteUserTasks(int userId) {
		try {
			log.info("Deleting the task!");
			entityManager
					.createQuery(
							"update  Task set status=:status where user.id=:id")
					.setParameter("status", 0).setParameter("id", userId)
					.executeUpdate();
			log.info("Task Deleted!");
			return true;
		} catch (Exception e) {
			log.error("Task failed to delete! Message" + e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Task> getAllTask() {
		try {
			log.info("Retrieving the tasks!");
			return (ArrayList<Task>) entityManager
					.createQuery(
							"select task from Task task where task.status=:status")
					.setParameter("status", 1).getResultList();
		} catch (Exception e) {
			log.error("Tasks failed to retrieve! Message " + e.getMessage());
			return null;

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Task> getTaskForUser(int userId) {
		try {
			log.info("Retrieving the tasks for user!");
			return (ArrayList<Task>) entityManager
					.createQuery(
							"select task from Task task where task.user.id=:userId and task.status=:status")
					.setParameter("userId", userId).setParameter("status", 1)
					.getResultList();
		} catch (Exception e) {
			log.error("Tasks for user failed to retrieve! Message "
					+ e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Task> filterTaskByName(String taskName, int userId) {
		try {
			log.info("Filtering the tasks!");
			return (ArrayList<Task>) entityManager
					.createQuery(
							"select task from Task task where task.emer LIKE :emer and task.user.id=:userId and task.status=:status")
					.setParameter("emer", "%" + taskName + "%")
					.setParameter("userId", userId).setParameter("status", 1)
					.getResultList();
		} catch (Exception e) {
			log.error("Filtering the tasks failed! Message " + e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Task> filterTaskByGjendjeUser(String gjendje, int userId) {
		try {
			log.info("Filtering the tasks by gjendje!");
			return (ArrayList<Task>) entityManager
					.createQuery(
							"select task from Task task where task.gjendje.gjendje=:gjendje and task.user.id=:userId and task.status=:status")
					.setParameter("gjendje", gjendje)
					.setParameter("userId", userId).setParameter("status", 1)
					.getResultList();
		} catch (Exception e) {
			log.error("Filtering the tasks by gjendje for user failed! Message "
					+ e.getMessage());
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Task> filterTaskByGjendjeAdmin(String gjendje) {
		try {
			log.info("Filtering the tasks by gjendje!");
			return (ArrayList<Task>) entityManager
					.createQuery(
							"select task from Task task where task.gjendje.gjendje=:gjendje  and task.status=:status")
					.setParameter("gjendje", gjendje).setParameter("status", 1)
					.getResultList();
		} catch (Exception e) {
			log.error("Filtering the tasks by gjendje for admin failed! Message "
					+ e.getMessage());
			return null;
		}
	}

}
