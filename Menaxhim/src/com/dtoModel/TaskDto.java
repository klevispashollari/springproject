package com.dtoModel;

import java.io.Serializable;

public class TaskDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String dateNisje;
	private String datePerfundimi;
	private String emer;
	private String pershkrim;
	private String emerPunonjes;
	private String mbiemerPunonjes;
	private int userId;
	private int gjendjeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateNisje() {
		return dateNisje;
	}

	public void setDateNisje(String dateNisje) {
		this.dateNisje = dateNisje;
	}

	public String getDatePerfundimi() {
		return datePerfundimi;
	}

	public void setDatePerfundimi(String datePerfundimi) {
		this.datePerfundimi = datePerfundimi;
	}

	public String getEmer() {
		return emer;
	}

	public void setEmer(String emer) {
		this.emer = emer;
	}

	public String getPershkrim() {
		return pershkrim;
	}

	public void setPershkrim(String pershkrim) {
		this.pershkrim = pershkrim;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmerPunonjes() {
		return emerPunonjes;
	}

	public void setEmerPunonjes(String emerPunonjes) {
		this.emerPunonjes = emerPunonjes;
	}

	public String getMbiemerPunonjes() {
		return mbiemerPunonjes;
	}

	public void setMbiemerPunonjes(String mbiemerPunonjes) {
		this.mbiemerPunonjes = mbiemerPunonjes;
	}

	public int getGjendjeId() {
		return gjendjeId;
	}

	public void setGjendjeId(int gjendjeId) {
		this.gjendjeId = gjendjeId;
	}

}
