package org.ff4j.springboot.sample.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a group with same center of interest.
 *
 * @author Digital Transformation LAB, Stream SOA
 */
//@Entity
//@Table(name="SHOWCASE_COMMUNITY")
@JsonIgnoreProperties
public class Community implements Serializable {
	
	/** serial. */
	private static final long serialVersionUID = 4558987644709975252L;

	/** Identifier. */
	
	private String name;
	
	/** Employee list. */
	private List < Employee > employees;

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
	 * Getter accessor for attribute 'employees'.
	 *
	 * @return
	 *       current value of 'employees'
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * Setter accessor for attribute 'employees'.
	 * @param employees
	 * 		new value for 'employees '
	 */
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
