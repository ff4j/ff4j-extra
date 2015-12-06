package org.ff4j.springboot.sample.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ff4j.springboot.sample.apibean.CompanyApiBean;
import org.ff4j.springboot.sample.apibean.DepartmentApiBean;
import org.ff4j.springboot.sample.apibean.EmployeeApiBean;
import org.ff4j.springboot.sample.apibean.ReferenceStrategy;
import org.ff4j.springboot.utils.ApiUtils;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/v1/departments/{uid}")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/v1/departments/{uid}", description = "CRUD Operations on the 'Department' entity")
public class DepartmentResource extends AbstractHrResource {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Read information about an Employee", response=EmployeeApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Information about Employee"), 
        @ApiResponse(code = 404, message= "Employee not found") })
    public Response read(@PathParam("uid") 
    					 @ApiParam(value = "Unique identifier for the employee", required = true) 
    					 Integer id,
    					
    					 @QueryParam("employees") 
						 @DefaultValue("LINK")
						 @ApiParam(value = "Strategy to fetch the employees", required = false, defaultValue= "LINK", allowableValues="NONE,LINK,CONTENT")
						 String stremployees,
	
						 @QueryParam("company") 
						 @DefaultValue("LINK")
						 @ApiParam(value = "Strategy to fetch the employees", required = false, defaultValue= "LINK", allowableValues="NONE,LINK,CONTENT")
						 String strcompany) {

		// 		Convert String parameter into target Enum and raise a 400 BAD REQUEST if not valid
		ReferenceStrategy employees = ApiUtils.getEnumFromString(ReferenceStrategy.class, stremployees);
		ReferenceStrategy company = ApiUtils.getEnumFromString(ReferenceStrategy.class, strcompany);	
      	
       // Check existence, eventually return 404
       if (!departementRepository.exists(id)) {
    	   return Response.status(Response.Status.NOT_FOUND).entity("HTTP 404 (NOT FOUND) - The Departments '" + id + "' does not exist").build();
       }
       
       // Load employee and display compan as a link (arbitrary choice when design)
       DepartmentApiBean dab = new DepartmentApiBean(departementRepository.findOne(id), employees,company);
       return Response.ok(dab).build();
    }
	
	@GET
	@Path("/company")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Read company about an Department", response=CompanyApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Company about Employee"), 
        @ApiResponse(code = 404, message= "Employee not found") })
    public Response readCompany(@PathParam("uid") 
    					 @ApiParam(value = "Unique identifier for the employee", required = true) 
    					 Integer id) {
      	
		// Check existence, eventually return 404
	    if (!departementRepository.exists(id)) {
	    	   return Response.status(Response.Status.NOT_FOUND).entity("HTTP 404 (NOT FOUND) - The Departments '" + id + "' does not exist").build();
	    }
       
       // Load employee and display compan as a link (arbitrary choice when design)
	   DepartmentApiBean dab = new DepartmentApiBean(departementRepository.findOne(id), ReferenceStrategy.NONE, ReferenceStrategy.CONTENT);
       return  Response.ok(dab.getCompany()).build();
    }
	
	@GET
	@Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Read company about an Department", response=CompanyApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Company about Employee"), 
        @ApiResponse(code = 404, message= "Employee not found") })
    public Response readEmployees(@PathParam("uid") 
    					 @ApiParam(value = "Unique identifier for the deparment", required = true) 
    					 Integer id) {
      	
		// Check existence, eventually return 404
	    if (!departementRepository.exists(id)) {
	    	   return Response.status(Response.Status.NOT_FOUND).entity("HTTP 404 (NOT FOUND) - The Departments '" + id + "' does not exist").build();
	    }
       
       // Load employee and display compan as a link (arbitrary choice when design)
	   DepartmentApiBean dab = new DepartmentApiBean(departementRepository.findOne(id), ReferenceStrategy.CONTENT, ReferenceStrategy.NONE);
       return  Response.ok(dab.getEmployees()).build();
    }
	
	@DELETE
	@ApiOperation(value= "Delete a Department by its ID", response=DepartmentApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 404, message= "Department has not been found"), 
        @ApiResponse(code = 204, message= "No content, department is deleted"),
        @ApiResponse(code = 400, message= "BadInvalid department identifier"),
        })
	public Response delete(@PathParam("uid") 
						   @ApiParam(value = "Unique identifier for the department", required = true) 
						   Integer id) {
		if (departementRepository.exists(id)) {
			departementRepository.delete(id);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("The department '" + id + "' does not exist").build();
	}
	
	 @PUT
	 @ApiOperation(value= "Update existing department", response=Response.class)
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 @ApiResponses({
	        @ApiResponse(code = 404, message= "The employee has not been found"), 
	        @ApiResponse(code = 202, message= "Accepted, employee has been updated") })
	 public Response update(
			 	@PathParam("uid")
	    		@ApiParam(value = "Unique identifier for the employee", required = true)
	    		Integer id, 
	    		
	    		@ApiParam(value = "Full object to be store as deparment", required = true)
	    		DepartmentApiBean apiBean) {
	    	
	    	if (departementRepository.exists(id)) {
	    		departementRepository.save(apiBean.asDepartment());
	    		Response.accepted().entity("deparment has been updated").build();
	    	}
	        return Response.status(Response.Status.BAD_REQUEST).entity("ERROR").build();
	    }
	 
	 
}
