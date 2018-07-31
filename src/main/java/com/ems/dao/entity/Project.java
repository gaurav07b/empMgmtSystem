package com.ems.dao.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="proj_id")
	private Integer pId;
	@Column(name="proj_name")
	private String pName;
	@Column(name="proj_location")
	private String pLocation;
	@ManyToMany(mappedBy = "projects")
    private List<Employees> employees = new ArrayList<>();

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpLocation() {
		return pLocation;
	}

	public void setpLocation(String pLocation) {
		this.pLocation = pLocation;
	}

	public List<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}

	public Project(Integer pId, String pName, String pLocation, List<Employees> employees) {
		super();
		this.pId = pId;
		this.pName = pName;
		this.pLocation = pLocation;
		this.employees = employees;
	}

	public Project() {
		super();
	}
	
}
