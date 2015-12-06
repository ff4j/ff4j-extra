package org.ff4j.springboot.sample.apibean;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Id;

import org.ff4j.springboot.sample.entity.Company;
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
@ApiModel(value="CompanyBean")
public class CompanyApiBean {
	
	@ApiModelProperty(value = "Unique identifier", notes="primary key in database", required=true, dataType="Integer")
	@Id
	private Integer idCompany = null;
	
	@ApiModelProperty(value = "Legal siret number for the company")
	private String siret = null;

	@ApiModelProperty(value = "Legal name for this company")
	private String name = null;
	
	@ApiModelProperty(value = "Short introduction to the company")
	private String description = null;
	
	@ApiModelProperty(value = "List of employee (full beans)")
	private Set < EmployeeApiBean > employees = null;
	
	@ApiModelProperty(value = "List of departments (full beans)")
	private Set < DepartmentApiBean > departments = null;
	
	@ApiModelProperty(value = "Reference to other entities")
	private CompanyApiBeanLinks links = null;
	
	/**
	 * Default constructor (required for instrospection)
	 */
	public CompanyApiBean() {
	}
	
	/**
	 * Representation of the Company.
	 *
	 * @param jpaBean
	 * 		current jpaBean
	 */
	public CompanyApiBean(Company jpaBean, ReferenceStrategy empStrategy, ReferenceStrategy depStrategy) {
		this.idCompany 	 = jpaBean.getIdCompany();
		this.siret 		 = jpaBean.getSiret();
		this.name 		 = jpaBean.getName();
		this.description = jpaBean.getDescription();
		this.links 		 = new CompanyApiBeanLinks(JaxRsLinkBuilder.linkTo(BaseHrResource.class).slash("companies").slash(idCompany).toString());
		switch(empStrategy) {
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
		switch(depStrategy) {
		// if none, do not map employees
		case NONE: break;
		case CONTENT:
			departments = new LinkedHashSet<>();
			jpaBean.getDepartments().stream().								 // loop over employees
				forEach(emp->departments.add(
						new DepartmentApiBean(emp, ReferenceStrategy.NONE, ReferenceStrategy.NONE))); // Create APIBean
		break;
		case LINK:
			jpaBean.getDepartments().stream().	// loop over employees
			forEach(dep->links.addDepartmentLink(
					JaxRsLinkBuilder.linkTo(BaseHrResource.class).slash("departments").slash(dep.getIdDepartment()).toString()));
		break;
		default:
			throw new IllegalArgumentException("Invalid value for query param emp, expected  " + ReferenceStrategy.values());
	}
	}
	
	/**
	 * Get back to JPA for persisting.
	 *
	 * @return
	 * 		company JPA bean
	 */
	public Company asCompany() {
		Company company = new Company();
		company.setIdCompany(getIdCompany());
		company.setName(getName());
		company.setDescription(getDescription());
		company.setSiret(getSiret());
		return company;
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
	 * Getter accessor for attribute 'links'.
	 *
	 * @return
	 *       current value of 'links'
	 */
	public CompanyApiBeanLinks getLinks() {
		return links;
	}

	/**
	 * Setter accessor for attribute 'links'.
	 * @param links
	 * 		new value for 'links '
	 */
	public void setLinks(CompanyApiBeanLinks links) {
		this.links = links;
	}

	/**
	 * Getter accessor for attribute 'departments'.
	 *
	 * @return
	 *       current value of 'departments'
	 */
	public Set<DepartmentApiBean> getDepartments() {
		return departments;
	}

	/**
	 * Setter accessor for attribute 'departments'.
	 * @param departments
	 * 		new value for 'departments '
	 */
	public void setDepartments(Set<DepartmentApiBean> departments) {
		this.departments = departments;
	}
	

}
