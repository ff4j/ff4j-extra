package org.ff4j.springboot.sample.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ff4j.springboot.sample.apibean.CompanyApiBean;
import org.ff4j.springboot.sample.apibean.CompanyApiBeanPage;
import org.ff4j.springboot.sample.apibean.DepartmentApiBean;
import org.ff4j.springboot.sample.apibean.EmployeeApiBean;
import org.ff4j.springboot.sample.apibean.ReferenceStrategy;
import org.ff4j.springboot.sample.entity.Company;
import org.ff4j.springboot.sample.entity.Department;
import org.ff4j.springboot.sample.entity.Employee;
import org.ff4j.springboot.utils.ApiUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.codahale.metrics.annotation.Timed;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;

@Component
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/v1", description = "Human Resource Services")
public class BaseHrResource extends AbstractHrResource {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response name() {
		return Response.ok("Human Resource Service").build();
	}
	
	/**
	 * Search company, load Department but lazy load on Employees.
	 */
	@GET
	@Path("/companies")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "List all available companies", 
		notes= "if not company return a empty list",
		response=CompanyApiBean.class, 
		responseContainer= "List",
		nickname="listCompanies")
    @ApiResponse(code = 200, message= "Success, return the list of companies")
	@Timed
	public Response findAllCompany(
		@QueryParam("employees") @DefaultValue("NONE")
		@ApiParam(value = "Strategy to list employees", required = false, defaultValue= "NONE", allowableValues="NONE,LINK,CONTENT")
		String employees,
		
		@QueryParam("departments") @DefaultValue("NONE")
		@ApiParam(value = "Strategy to list employees", required = false, defaultValue= "NONE", allowableValues="NONE,LINK,CONTENT")
		String departemnts) {
		
		// Convert String parameter into target Enum and raise a 400 BAD REQUEST if value not valid for enum
		ReferenceStrategy strategyEmp = ApiUtils.getEnumFromString(ReferenceStrategy.class, employees);
		
		// Convert String parameter into target Enum and raise a 400 BAD REQUEST if value not valid for enum
		ReferenceStrategy strategyDepart = ApiUtils.getEnumFromString(ReferenceStrategy.class, departemnts);
		
		// Invoke dao...
		Iterable<Company> daoResult = companyRepository.findAll();
		
		// Avoid null pointer, return an empty list
		if (daoResult == null) {
			return Response.ok().entity(new ArrayList<>()).build();
		}
		
		// Convert output from jpa to Api Beans
		return Response.ok().entity(
				StreamSupport.stream(daoResult.spliterator(), false).	// Run each item iteratively (keep ordered)
				map(t->new CompanyApiBean(t, strategyEmp, strategyDepart)).	   			// Change relationship with employees
				collect(Collectors.toList())).build();					// Output as List<>
	}
	
	@GET
	@Timed
	@Path("/companiesPageable")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "List all available companies in pages",
		response=CompanyApiBean.class, 
		responseContainer= "List",
		nickname="listCompanies")
    @ApiResponse(code = 200, message= "Success, return a page of companies")
	public Response findAllCompaniesPageable(
	        @QueryParam("page") @DefaultValue("0") int page,
	        @QueryParam("size") @DefaultValue("20") int size,
	        @QueryParam("sort") @DefaultValue("idCompany") List<String> sort,
	        @QueryParam("direction") @DefaultValue("asc") String direction,
	       
	        @QueryParam("employees") @DefaultValue("NONE")
			@ApiParam(value = "Strategy to list employees", required = false, defaultValue= "NONE", allowableValues="NONE,LINK,CONTENT")
			String employees,
			
			@QueryParam("departments") @DefaultValue("NONE")
			@ApiParam(value = "Strategy to list employees", required = false, defaultValue= "NONE", allowableValues="NONE,LINK,CONTENT")
			String departemnts) {
		
		// Convert String parameter into target Enum and raise a 400 BAD REQUEST if not valid
		ReferenceStrategy strategyEmp = ApiUtils.getEnumFromString(ReferenceStrategy.class, employees);

		// Convert String parameter into target Enum and raise a 400 BAD REQUEST if not valid
		ReferenceStrategy strategyDep = ApiUtils.getEnumFromString(ReferenceStrategy.class, departemnts);
				
		// Creation request from parameters
		PageRequest pageReq = new PageRequest(page, size, Sort.Direction.fromString(direction), sort.toArray(new String[0]));
		
		// Call dao with dedicated request
		Page<Company> pageResult = companyRepository.findAll(pageReq);
		
		// Convert jpaBeans into apiBeans
		List <CompanyApiBean> content = 
				StreamSupport.stream(pageResult.spliterator(), false).		// Split items but keep ordered (no parallel)
				map(t->new CompanyApiBean(t, strategyEmp, strategyDep)).	// Change relationship with employees
				collect(Collectors.toList());								// Get back a list
		
		// Prepare output with page and previous/next links if relevant
		int totalElements = new Long(pageResult.getTotalElements()).intValue();
		CompanyApiBeanPage currentPage = new CompanyApiBeanPage(content, pageReq , totalElements);
		
		// Return data
		return Response.ok().entity(currentPage).build();
	}
	
	/**
	 * Identifier is generated and is not know in advance, cannot use PUT method.
	 * @param company
	 *		current company
	 * @return
	 * 	
	 */
	@POST
	@Timed
	@Path("/companies")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "Create a new company")
	public Response createCompany(@NotNull CompanyApiBean companyApiBean) {
		Company result = companyRepository.save(companyApiBean.asCompany());
		
	    URI location = uriInfo.getAbsolutePathBuilder().path("{uid}").resolveTemplate("uid", result.getIdCompany()).build();
	    
	    return Response.created(location).build();
	}
	
	/**
	 * Search company, load Department but lazy load on Employees.
	 */
	@GET
	@Path("/employees")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "List all available employees", 
		notes= "if there are no employee, return an empty list",
		response=EmployeeApiBean.class, responseContainer= "List",nickname="listEmployees")
    @ApiResponse(code = 200, message= "Success, return the list of employees")
	public Response findAllEmployees(
		@QueryParam("company") 
		@DefaultValue("LINK")
		@ApiParam(value = "Strategy to fetch the company from employee", required = false, defaultValue= "LINK", allowableValues="NONE,LINK,CONTENT")
		String company) {
		
		// Convert String parameter into target Enum and raise a 400 BAD REQUEST if not valid
		ReferenceStrategy strategy = ApiUtils.getEnumFromString(ReferenceStrategy.class, company);
				
		// Invoke dao...
		Iterable<Employee> daoResult = employeeRepository.findAll();
		
		// Avoid null pointer, return a empty list
		if (daoResult == null) {
			return Response.ok().entity(new ArrayList<>()).build();
		}
		
		// Check DAO return...
		return Response.ok().entity(
				StreamSupport.stream(daoResult.spliterator(), false).	// Run each item iteratively
				map(t->new EmployeeApiBean(t, strategy)).	   			// Change relationship with employees
				collect(Collectors.toList())).build();					// Output as List<>
	}
	
	
	
	/**
	 * Search company, load Department but lazy load on Employees.
	 */
	@GET
	@Path("/departments")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value= "List all available departments", 
		notes= "if there are no depertments, return an empty list",
		response=DepartmentApiBean.class, 
		responseContainer= "List",
		nickname="listEmployees")
    @ApiResponse(code = 200, message= "Success, return the list of employees")
	public Response findAllDepartments(
			@QueryParam("employees") 
			@DefaultValue("LINK")
			@ApiParam(value = "Strategy to fetch the employees", required = false, defaultValue= "LINK", allowableValues="NONE,LINK,CONTENT")
			String stremployees,
			
			@QueryParam("company") 
			@DefaultValue("LINK")
			@ApiParam(value = "Strategy to fetch the employees", required = false, defaultValue= "LINK", allowableValues="NONE,LINK,CONTENT")
			String strcompany) {
		
		// Convert String parameter into target Enum and raise a 400 BAD REQUEST if not valid
		ReferenceStrategy employees = ApiUtils.getEnumFromString(ReferenceStrategy.class, stremployees);
		ReferenceStrategy company = ApiUtils.getEnumFromString(ReferenceStrategy.class, strcompany);		
				
		// Invoke dao...
		Iterable<Department> daoResult = departementRepository.findAll();
		
		// Avoid null pointer, return a empty list
		if (daoResult == null) {
			return Response.ok().entity(new ArrayList<>()).build();
		}
		
		// Check DAO return...
		return Response.ok().entity(
				StreamSupport.stream(daoResult.spliterator(), false).	// Run each item iteratively
				map(t->new DepartmentApiBean(t, employees, company)).	// Change relationship with employees & company at will
				collect(Collectors.toList())).build();					// Output as List<>
	}
	
}
