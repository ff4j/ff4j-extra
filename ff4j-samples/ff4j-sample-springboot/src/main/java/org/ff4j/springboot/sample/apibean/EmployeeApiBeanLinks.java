package org.ff4j.springboot.sample.apibean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties			// You should not raise error is field not found
@JsonInclude(Include.NON_NULL)	// You should not display null values
public class EmployeeApiBeanLinks {
	
	@ApiModelProperty(value = "Push link to company")
	private String company = null;
	
	@ApiModelProperty(value = "URL to access this resources")
	private String self = null;
	
	public EmployeeApiBeanLinks() {
	}
	
	public EmployeeApiBeanLinks(String uri) {
		this.self = uri;
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
