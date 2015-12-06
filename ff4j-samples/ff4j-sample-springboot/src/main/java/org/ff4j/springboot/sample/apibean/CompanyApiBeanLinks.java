package org.ff4j.springboot.sample.apibean;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * This bean is created to represent JPA relation ship as LINK or and self reference.
 * 
 * @author Digital Transformation LAB, Stream SOA
 */
@JsonIgnoreProperties			// You should not raise error is field not found
@JsonInclude(Include.NON_NULL)	// You should not display null values
public class CompanyApiBeanLinks {

	@ApiModelProperty(value = "Reference to list of employee")
	private Set < String > employees = null;
	
	@ApiModelProperty(value = "Reference to list of departments")
	private Set < String > departments = null;
	
	@ApiModelProperty(value = "URL to access this resources")
	private String self = null;
	
	/**
	 * Default constructor (required for dynamic instanciation)
	 */
	public CompanyApiBeanLinks() {
	}
	
	/**
	 * Constructor with the selft URI
	 * @param uri
	 * 		api uri
	 */
	public CompanyApiBeanLinks(String uri) {
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
	 * Utility to add an employee and create list if not exist.
	 *
	 * @param uri
	 */
	public void addDepartmentLink(String uri) {
		if (departments == null) {
			departments = new HashSet<>();
		}
		departments.add(uri);
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

	/**
	 * Getter accessor for attribute 'departments'.
	 *
	 * @return
	 *       current value of 'departments'
	 */
	public Set<String> getDepartments() {
		return departments;
	}

	/**
	 * Setter accessor for attribute 'departments'.
	 * @param departments
	 * 		new value for 'departments '
	 */
	public void setDepartments(Set<String> departments) {
		this.departments = departments;
	}
	
}
