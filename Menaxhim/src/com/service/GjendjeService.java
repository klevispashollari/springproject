package com.service;

import java.util.ArrayList;

import com.dtoModel.GjendjeDto;

public interface GjendjeService {

	public ArrayList<GjendjeDto> getAllGjendje();

	public boolean updateGjendje(int taskId, int gjendjeId);
}
