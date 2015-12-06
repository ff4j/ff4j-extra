package org.ff4j.springboot.sample.apibean;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Id;

import org.ff4j.springboot.sample.entity.Company;
import org.ff4j.springboot.sample.entity.Department;
import org.ff4j.springboot.sample.resources.BaseHrResource;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Represent a {@link Company} as JSON.
 * 
 * @author Digital Transformation LAB, Stream SOA
 */
@JsonIgnoreProperties			// You should not raise error is field not found
@JsonInclude(Include.NON_NULL)	// You should not display null values
@ApiModel(value="DepartmentBean")
public class DepartmentApiBean {
	
	@ApiModelProperty(value = "Unique identifier", notes="primary key in database", required=true, dataType="Integer")
	@Id
	private Integer idDepartment = null;
	
	@ApiModelProperty(value = "Legal name for this department")
	private String name = null;
	
	@ApiModelProperty(value = "Short introduction to the company")
	private String description = null;
	
	@ApiModelProperty(value = "Reference bean to company")
	private CompanyApiBean company = null;
	
	@ApiModelProperty(value = "List of employee (full beans)")
	private Set < EmployeeApiBean > employees = null;
	
	@ApiModelProperty(value = "Reference to other entities")
	private DepartmentApiBeanLinks links = null;
	
	/**
	 * Default constructor (required for instrospection)
	 */
	public DepartmentApiBean() {
	}
	
	/**
	 * Representation of the Company.
	 *
	 * @param jpaBean
	 * 		current jpaBean
	 */
	public DepartmentApiBean(Department jpaBean, ReferenceStrategy stratEmployees, ReferenceStrategy company) {
		this.idDepartment 	 = jpaBean.getIdDepartment();
		this.name 		 = jpaBean.getName();
		this.links 		 = new DepartmentApiBeanLinks(
				JaxRsLinkBuilder.linkTo(BaseHrResource.class).slash("departments").slash(idDepartment).toString());
		
		switch(stratEmployees) {
			// if none, do not map employees
			case NONE: break;
			case CONTENT:
				employees = new LinkedHashSet<>();
				jpaBean.getEmployees().stream().								 // loop over employees
					forEach(emp->employees.add(new EmployeeApiBean(emp, ReferenceStrategy.NONE))); // Create APIBean
			break;
			case LINK:
				jpaBean.getEmployees().stream().	// loop over employees
				forEach(emp->links.addEmployeeLink(
						JaxRsLinkBuilder.linkTo(BaseHrResource.class).slash("employees").slash(emp.getIdEmployee()).toString()));
			break;
			default:
				throw new IllegalArgumentException("Invalid value for query param emp, expected  " + ReferenceStrategy.values());
		}
		
		switch(company) {
			case NONE :
				// no link, no company
			break;
			
			case LINK :
				this.links.setCompany(
						JaxRsLinkBuilder.linkTo(BaseHrResource.class).slash("companies").slash(jpaBean.getCompany().getIdCompany()).toString());
			break;
			
			case CONTENT :
				this.company = new CompanyApiBean(jpaBean.getCompany(), ReferenceStrategy.NONE, ReferenceStrategy.NONE);
			break;
	}
		
		
	}
	
	/**
	 * Get back to JPA for persisting.
	 *
	 * @return
	 * 		company JPA bean
	 */
	public Department asDepartment() {
		Department dep = new Department();
		dep.setIdDepartment(getIdDepartment());
		dep.setName(getName());
		dep.setCompany(getCompany().asCompany());
		return dep;
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
	public Set<EmployeeApiBean> getEmployees() {
		return employees;
	}

	/**
	 * Setter accessor for attribute 'employees'.
	 * @param employees
	 * 		new value for 'employees '
	 */
	public void setEmployees(Set<EmployeeApiBean> employees) {
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
	public DepartmentApiBeanLinks getLinks() {
		return links;
	}

	/**
	 * Setter accessor for attribute 'links'.
	 * @param links
	 * 		new value for 'links '
	 */
	public void setLinks(DepartmentApiBeanLinks links) {
		this.links = links;
	}
	

}
