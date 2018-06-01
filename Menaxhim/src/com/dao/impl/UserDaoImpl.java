package com.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.entitete.User;

@Repository(value = "UserDao")
@Scope("singleton")
public class UserDaoImpl implements UserDao {

	private static Logger log = Logger.getLogger(UserDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public boolean add(User user) {
		try {
			log.info("Adding the user!");
			entityManager.persist(user);
			log.info("User added!");
			return true;
		} catch (Exception e) {
			log.error("User failed to add! Message " + e.getMessage());
			return false;
		}
	}

	@Override
	public boolean edit(User user) {
		try {
			log.info("Editing the user!");
			entityManager
					.createQuery(
							"update User set fname=:fname , lname=:lname , email=:email where id=:id")
					.setParameter("fname", user.getFname())
					.setParameter("lname", user.getLname())
					.setParameter("email", user.getEmail())
					.setParameter("id", user.getId()).executeUpdate();
			log.info("User edited!");
			return true;
		} catch (Exception e) {
			log.error("User failed to add! Message " + e.getMessage());
			return false;

		}
	}

	@Override
	public boolean delete(int userId) {
		try {
			log.info("Deleting the user!");
			entityManager
					.createQuery("update User set status=:status where id=:id")
					.setParameter("status", 0).setParameter("id", userId)
					.executeUpdate();
		
			log.info("User deleted!");
			return true;

		} catch (Exception e) {
			log.error("user failed to delete! Message " + e.getMessage());
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<User> getAllUser() {
		try {
			log.info("Retrieving the users!");
			return (ArrayList<User>) entityManager
					.createQuery(
							"select user from User user where user.status=:status")
					.setParameter("status", 1).getResultList();
		} catch (Exception e) {
			log.error("Users failed to retrieve! Message " + e.getMessage());
			return null;
		}

	}

	@Override
	public User getLoggedUser(String email) {
		try {
			log.info("Getting the user from email!");
			return (User) entityManager
					.createQuery(
							"select user from User user where user.email=:email  and user.status!=:status")
					.setParameter("email", email).setParameter("status", 0)
					.getSingleResult();
		} catch (Exception e) {
			log.error("User failed to retrieve from email! Message "
					+ e.getMessage());
			return null;
		}

	}

	@Override
	public User getUserFromId(int userId) {
		try {
			log.info("Getting the user from id!");
			return (User) entityManager
					.createQuery(
							"select user from User user where user.id=:userId")
					.setParameter("userId", userId).getSingleResult();

		} catch (Exception e) {
			log.error("User failed to retrieve from id! Message "
					+ e.getMessage());
			return null;
		}
	}

	@Override
	public boolean changePassword(User user) {
		try {
			entityManager
					.createQuery(
							"update User  set password =:password where id=:id")
					.setParameter("password", user.getPassword())
					.setParameter("id", user.getId()).executeUpdate();
			log.info("Password changed!");
			return true;
		} catch (Exception e) {
			log.error("Password failed to change! Message " + e.getMessage());
			return false;

		}
	}
}