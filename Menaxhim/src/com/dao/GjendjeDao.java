package com.dao;

import java.util.ArrayList;

import com.entitete.Gjendje;

public interface GjendjeDao {

	public ArrayList<Gjendje> getAllGjendje();

	public boolean updateGjendje(int taskId, int gjendjeId);

}
