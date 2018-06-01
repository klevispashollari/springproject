package com.entitete;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "emer")
	private String emer;
	@Column(name = "pershkrim")
	private String pershkrim;
	@Column(name = "date_nisje")
	private Timestamp dateNisje;
	@Column(name = "date_perfundimi")
	private Timestamp datePerfundimi;
	@Column(name = "status")
	private int status;
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User user;

	 @ManyToOne()
	 @JoinColumn(name = "gjendje_id")
	 private Gjendje gjendje;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Timestamp getDateNisje() {
		return dateNisje;
	}

	public void setDateNisje(Timestamp dateNisje) {
		this.dateNisje = dateNisje;
	}

	public Timestamp getDatePerfundimi() {
		return datePerfundimi;
	}

	public void setDatePerfundimi(Timestamp datePerfundimi) {
		this.datePerfundimi = datePerfundimi;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gjendje getGjendje() {
		return gjendje;
	}

	public void setGjendje(Gjendje gjendje) {
		this.gjendje = gjendje;
	}

}