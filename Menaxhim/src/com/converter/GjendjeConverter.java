package com.converter;

import com.dtoModel.GjendjeDto;
import com.entitete.Gjendje;

public class GjendjeConverter {

	public static GjendjeDto toGjendjeDto(Gjendje gjendje) {
		GjendjeDto gjendjeDto = new GjendjeDto();
		gjendjeDto.setId(gjendje.getId());
		gjendjeDto.setGjendje(gjendje.getGjendje());
		return gjendjeDto;
	}
}
