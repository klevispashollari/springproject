package com.dao.impl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.dao.GjendjeDao;
import com.entitete.Gjendje;

@Repository(value = "GjendjeDao")
@Scope("singleton")
class GjendjeDaoImpl implements GjendjeDao {

	private static Logger log = Logger.getLogger(GjendjeDaoImpl.class);

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Gjendje> getAllGjendje() {
		try {
			log.info("Retrieving Gjendjet");
			return (ArrayList<Gjendje>) entityManager.createQuery(
					"select gjendje from Gjendje gjendje").getResultList();
		} catch (Exception e) {
			log.error("Gjendjet failed to retrieve! Message" + e.getMessage());
			return null;
		}
	}

	@Override
	public boolean updateGjendje(int taskId, int gjendjeId) {
		try {
			log.info("Updating status of the task!");
			entityManager
					.createQuery(
							"update Task set gjendje.id=:gjendjeId where id=:taskId ")
					.setParameter("gjendjeId", gjendjeId)
					.setParameter("taskId", taskId).executeUpdate();
			log.info("Status of the task updated!");
			return true;
		} catch (Exception e) {
			log.error("Status of the task failed to update! Message "
					+ e.getMessage());
			return false;
		}
	}
}
