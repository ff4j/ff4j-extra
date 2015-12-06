package org.ff4j.springboot.sample.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="SHOWCASE_EMPLOYEE")
@JsonIgnoreProperties			// You should not raise error is field not found
@JsonInclude(Include.NON_NULL)	// You should not display null values
public class Employee implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 665562338418826573L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idemployee")
	private Integer idEmployee;
	
	@Basic(optional = true)
	@Column(name="firstname", nullable = true)
	private String firstName;
	
	@Basic(optional = true)
	@Column(name="lastname", nullable = true)
	private String lastName;
	
	@Basic(optional = false)
	@Column(name="email", nullable = false)
	private String email;
	
	@ManyToOne(optional = false, cascade = CascadeType.DETACH)
	@JoinColumn(name="company", referencedColumnName="idcompany")
	private Company company;

	@ManyToOne(optional = true, cascade = CascadeType.DETACH)
	@JoinColumn(name="department", referencedColumnName="iddepartment")
	private Department department;
	
	//private List < Community > communities;

	/**
	 * Getter accessor for attribute 'firstName'.
	 *
	 * @return
	 *       current value of 'firstName'
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter accessor for attribute 'firstName'.
	 * @param firstName
	 * 		new value for 'firstName '
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter accessor for attribute 'lastName'.
	 *
	 * @return
	 *       current value of 'lastName'
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter accessor for attribute 'lastName'.
	 * @param lastName
	 * 		new value for 'lastName '
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter accessor for attribute 'email'.
	 *
	 * @return
	 *       current value of 'email'
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter accessor for attribute 'email'.
	 * @param email
	 * 		new value for 'email '
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter accessor for attribute 'idEmployee'.
	 *
	 * @return
	 *       current value of 'idEmployee'
	 */
	public Integer getIdEmployee() {
		return idEmployee;
	}

	/**
	 * Setter accessor for attribute 'idEmployee'.
	 * @param idEmployee
	 * 		new value for 'idEmployee '
	 */
	public void setIdEmployee(Integer idEmployee) {
		this.idEmployee = idEmployee;
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
	
}
