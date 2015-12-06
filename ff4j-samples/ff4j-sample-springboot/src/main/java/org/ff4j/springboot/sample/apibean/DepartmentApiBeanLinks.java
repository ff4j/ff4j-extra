package org.ff4j.springboot.sample.apibean;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties			// You should not raise error is field not found
@JsonInclude(Include.NON_NULL)	// You should not display null values
public class DepartmentApiBeanLinks {
	
	@ApiModelProperty(value = "Push link to company")
	private String company = null;
	
	@ApiModelProperty(value = "Reference to list of employee")
	private Set < String > employees = null;
	
	@ApiModelProperty(value = "URL to access this resources")
	private String self = null;

	/**
	 * Default constructor (required for dynamic instanciation)
	 */
	public DepartmentApiBeanLinks() {
	}
	
	/**
	 * Constructor with the selft URI
	 * @param uri
	 * 		api uri
	 */
	public DepartmentApiBeanLinks(String uri) {
		this.self = uri;
	}
	
	/**
	 * Utility to add an employee and create list if not exist.
	 *
	 * @param uri
	 */
	public void addEmployeeLink(String uri) {
		if (employees == null) {
			employees = new HashSet<>();
		}
		employees.add(uri);
	}
	
	/**
	 * Getter accessor for attribute 'company'.
	 *
	 * @return
	 *       current value of 'company'
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * Setter accessor for attribute 'company'.
	 * @param company
	 * 		new value for 'company '
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * Getter accessor for attribute 'employees'.
	 *
	 * @return
	 *       current value of 'employees'
	 */
	public Set<String> getEmployees() {
		return employees;
	}

	/**
	 * Setter accessor for attribute 'employees'.
	 * @param employees
	 * 		new value for 'employees '
	 */
	public void setEmployees(Set<String> employees) {
		this.employees = employees;
	}

	/**
	 * Getter accessor for attribute 'self'.
	 *
	 * @return
	 *       current value of 'self'
	 */
	public String getSelf() {
		return self;
	}

	/**
	 * Setter accessor for attribute 'self'.
	 * @param self
	 * 		new value for 'self '
	 */
	public void setSelf(String self) {
		this.self = self;
	}

}
