package com.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.converter.GjendjeConverter;
import com.dao.GjendjeDao;
import com.dtoModel.GjendjeDto;
import com.entitete.Gjendje;
import com.service.GjendjeService;

@Service("gjendjeService")
public class GjendjeServiceImpl implements GjendjeService {

	@Autowired
	GjendjeDao gjendjeDao;

	@Transactional
	public ArrayList<GjendjeDto> getAllGjendje() {
		ArrayList<GjendjeDto> gjendjeDtoList = new ArrayList<>();
		ArrayList<Gjendje> gjendjeList = gjendjeDao.getAllGjendje();
		if (gjendjeList != null) {
			for (int i = 0; i < gjendjeList.size(); i++) {
				gjendjeDtoList.add(GjendjeConverter.toGjendjeDto(gjendjeList
						.get(i)));
			}
		}
		return gjendjeDtoList;

	}

	@Transactional
	public boolean updateGjendje(int taskId, int gjendjeId) {
		return gjendjeDao.updateGjendje(taskId, gjendjeId);
	}
}
