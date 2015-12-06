package org.ff4j.springboot.sample.apibean;

import java.io.Serializable;

import javax.persistence.Id;

import org.ff4j.springboot.sample.entity.Employee;
import org.ff4j.springboot.sample.resources.BaseHrResource;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties			// You should not raise error is field not found
@JsonInclude(Include.NON_NULL)	// You should not display null values
@ApiModel(value="EmployeeBean")
public class EmployeeApiBean implements Serializable {

	/** Serial.*/
	private static final long serialVersionUID = -1833162707442459675L;

	/** legal siret number. */
	@Id
	@ApiModelProperty(value = "Unique identifier", notes="primary key in database", required=true, dataType="Integer")
	private Integer idEmployee = null;
	
	@ApiModelProperty(value = "Employee's first Name")
	private String firstName = null;
	
	@ApiModelProperty(value = "Employee's family name")
	private String lastName = null;
	
	@ApiModelProperty(value = "Employee's email adress")
	private String email = null;
	
	@ApiModelProperty(value = "Reference bean to company")
	private CompanyApiBean company = null;

	@ApiModelProperty(value = "Entities linked to employee")
	private EmployeeApiBeanLinks links = null;
	
	/** Target Employee. */
	public Employee asEmployee() {
		Employee emp = new Employee();
		emp.setEmail(getEmail());
		emp.setFirstName(getFirstName());
		emp.setLastName(getLastName());
		emp.setIdEmployee(getIdEmployee());
		emp.setCompany(getCompany().asCompany());
		return emp;
	}
	
	/**
	 * Representation of the Company.
	 *
	 * @param jpaBean
	 * 		current jpaBean
	 */
	public EmployeeApiBean(Employee jpaBean, ReferenceStrategy company) {
		this.idEmployee = jpaBean.getIdEmployee();
		this.email 		= jpaBean.getEmail();
		this.firstName 	= jpaBean.getFirstName();
		this.lastName 	= jpaBean.getLastName();
		this.links		= new EmployeeApiBeanLinks(JaxRsLinkBuilder.linkTo(BaseHrResource.class).slash("employees").slash(jpaBean.getIdEmployee()).toString());
		switch(company) {
			case NONE :
				// no link, no company
			break;
			
			case LINK :
				this.links.setCompany(JaxRsLinkBuilder.linkTo(BaseHrResource.class).slash("companies").slash(jpaBean.getCompany().getIdCompany()).toString());
			break;
			
			case CONTENT :
				this.company = new CompanyApiBean(jpaBean.getCompany(), ReferenceStrategy.NONE, ReferenceStrategy.NONE);
			break;
		}
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
	 * Getter accessor for attribute 'company'.
	 *
	 * @return
	 *       current value of 'company'
	 */
	public CompanyApiBean getCompany() {
		return company;
	}

	/**
	 * Setter accessor for attribute 'company'.
	 * @param company
	 * 		new value for 'company '
	 */
	public void setCompany(CompanyApiBean company) {
		this.company = company;
	}

	/**
	 * Getter accessor for attribute 'links'.
	 *
	 * @return
	 *       current value of 'links'
	 */
	public EmployeeApiBeanLinks getLinks() {
		return links;
	}

	/**
	 * Setter accessor for attribute 'links'.
	 * @param links
	 * 		new value for 'links '
	 */
	public void setLinks(EmployeeApiBeanLinks links) {
		this.links = links;
	}
}
