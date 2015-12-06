package org.ff4j.springboot.sample.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModelProperty;

/**
 * Represents the POJO to work with Companies.
 *
 * @author Digital Transformation LAB, Stream SOA
 */
@Entity							// Mark as JPA entity
@Table(name="SHOWCASE_COMPANY")	// Define target table name
public class Company implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -4438656661685903545L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idcompany")
	@ApiModelProperty(value = "Unique identifier for compagny")
	private Integer idCompany;
	
	@Basic(optional = false)
	@Column(name="siret", nullable = false)
	private String siret;

	@Column(name = "name", nullable = false)
	@Basic(optional = false)
	private String name;
	
	@Column(name = "description", nullable = true)
	@Basic(optional = true)
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
	private Set < Employee > employees = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
	private Set < Department > departments = new HashSet<>();
	
	/**
	 * Default constructor.
	 */
	public Company() {
	}
	
	/**
	 * Parameterized constructor.
	 *
	 * @param siret
	 * 			init value siret
	 * @param name
	 * 			init value name
	 * @param creatDate
	 * 			init value creation date
	 */
	public Company(String siret, String name) {
		this();
		this.siret = name;
		this.name = name;
	}

	/**
	 * Getter accessor for attribute 'siret'.
	 *
	 * @return
	 *       current value of 'siret'
	 */
	public String getSiret() {
		return siret;
	}

	/**
	 * Setter accessor for attribute 'siret'.
	 * @param siret
	 * 		new value for 'siret '
	 */
	public void setSiret(String siret) {
		this.siret = siret;
	}

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
	 * Getter accessor for attribute 'description'.
	 *
	 * @return
	 *       current value of 'description'
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter accessor for attribute 'description'.
	 * @param description
	 * 		new value for 'description '
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter accessor for attribute 'employees'.
	 *
	 * @return
	 *       current value of 'employees'
	 */
	public Set<? extends Employee> getEmployees() {
		return employees;
	}

	/**
	 * Setter accessor for attribute 'employees'.
	 * @param employees
	 * 		new value for 'employees '
	 */
	public void setEmployees(Set< Employee> employees) {
		this.employees = employees;
	}

	/**
	 * Getter accessor for attribute 'idCompany'.
	 *
	 * @return
	 *       current value of 'idCompany'
	 */
	public Integer getIdCompany() {
		return idCompany;
	}

	/**
	 * Setter accessor for attribute 'idCompany'.
	 * @param idCompany
	 * 		new value for 'idCompany '
	 */
	public void setIdCompany(Integer idCompany) {
		this.idCompany = idCompany;
	}

	/**
	 * Getter accessor for attribute 'departments'.
	 *
	 * @return
	 *       current value of 'departments'
	 */
	public Set<Department> getDepartments() {
		return departments;
	}

	/**
	 * Setter accessor for attribute 'departments'.
	 * @param departments
	 * 		new value for 'departments '
	 */
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

	/**
	 * Getter accessor for attribute 'departments'.
	 *
	 * @return
	 *       current value of 'departments'
	 *
	public Set<Department> getDepartments() {
		return departments;
	}

	/**
	 * Setter accessor for attribute 'departments'.
	 * @param departments
	 * 		new value for 'departments '
	 *
	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}*/
	
	
}
