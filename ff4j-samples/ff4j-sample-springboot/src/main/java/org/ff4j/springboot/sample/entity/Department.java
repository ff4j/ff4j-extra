package org.ff4j.springboot.sample.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="SHOWCASE_DEPARTMENT")
@JsonIgnoreProperties
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "iddepartment")
	private Integer idDepartment;
	
	@Column(name = "name", nullable = false)
	@Basic(optional = false)
	private String name;
	
	@ManyToOne(optional = false, cascade = CascadeType.DETACH)
	@JoinColumn(name="company", referencedColumnName="idcompany")
	private Company company;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "department")
	private Set < Employee > employees = new HashSet<>();

	/**
	 * Getter accessor for attribute 'name'.
	 *
	 * @return
	 *       current value of 'name'
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter accessor for attribute 'name'.
	 * @param name
	 * 		new value for 'name '
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter accessor for attribute 'company'.
	 *
	 * @return
	 *       current value of 'company'
	 */
	public Company getCompany() {
		return company;
	}

	/**
	 * Setter accessor for attribute 'company'.
	 * @param company
	 * 		new value for 'company '
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/**
	 * Getter accessor for attribute 'employees'.
	 *
	 * @return
	 *       current value of 'employees'
	 */
	public Set<Employee> getEmployees() {
		return employees;
	}

	/**
	 * Setter accessor for attribute 'employees'.
	 * @param employees
	 * 		new value for 'employees '
	 */
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * Getter accessor for attribute 'idDepartment'.
	 *
	 * @return
	 *       current value of 'idDepartment'
	 */
	public Integer getIdDepartment() {
		return idDepartment;
	}

	/**
	 * Setter accessor for attribute 'idDepartment'.
	 * @param idDepartment
	 * 		new value for 'idDepartment '
	 */
	public void setIdDepartment(Integer idDepartment) {
		this.idDepartment = idDepartment;
	}
}
