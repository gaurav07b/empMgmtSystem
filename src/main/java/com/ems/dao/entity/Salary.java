package com.ems.dao.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="salaries")
public class Salary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sal_id")
	private Integer salId;

	@OneToMany(mappedBy="salary")
	private Set<Employees> employees;
	
	@Column(name="salary")
	private Integer sal;
	
	@Column(name="date")
	private Date date;

	public Integer getSalId() {
		return salId;
	}

	public void setSalId(Integer salId) {
		this.salId = salId;
	}

	public Set<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employees> employees) {
		this.employees = employees;
	}

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Salary(Integer salId, Set<Employees> employees, Integer sal, Date date) {
		super();
		this.salId = salId;
		this.employees = employees;
		this.sal = sal;
		this.date = date;
	}

	public Salary() {
		super();
	}
	
}
