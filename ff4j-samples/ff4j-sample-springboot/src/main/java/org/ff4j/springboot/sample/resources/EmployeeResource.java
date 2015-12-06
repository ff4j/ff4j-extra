package org.ff4j.springboot.sample.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ff4j.springboot.sample.apibean.CompanyApiBean;
import org.ff4j.springboot.sample.apibean.EmployeeApiBean;
import org.ff4j.springboot.sample.apibean.ReferenceStrategy;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/v1/employees/{uid}")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/v1/employees/{uid}", description = "CRUD Operations on the Employee entity")
public class EmployeeResource extends AbstractHrResource {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Read information about an Employee", response=EmployeeApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Information about Employee"), 
        @ApiResponse(code = 404, message= "Employee not found") })
    public Response read(@PathParam("uid") 
    					 @ApiParam(value = "Unique identifier for the employee", required = true) 
    					 Integer id) {
      	
       // Check existence, eventually return 404
       if (!employeeRepository.exists(id)) {
    	   return Response.status(Response.Status.NOT_FOUND).entity("HTTP 404 (NOT FOUND) - The Employee '" + id + "' does not exist").build();
       }
       
       // Load employee and display compan as a link (arbitrary choice when design)
       return  Response.ok(new EmployeeApiBean(employeeRepository.findOne(id), ReferenceStrategy.LINK)).build();
    }
	
	@GET
	@Path("/company")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Read company about an Employee", response=CompanyApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Company about Employee"), 
        @ApiResponse(code = 404, message= "Employee not found") })
    public Response readCompany(@PathParam("uid") 
    					 @ApiParam(value = "Unique identifier for the employee", required = true) 
    					 Integer id) {
      	
       // Check existence, eventually return 404
       if (!employeeRepository.exists(id)) {
    	   return Response.status(Response.Status.NOT_FOUND).entity("HTTP 404 (NOT FOUND) - The Employee '" + id + "' does not exist").build();
       }
       
       // Load employee and display compan as a link (arbitrary choice when design)
       CompanyApiBean apiBean = new EmployeeApiBean(employeeRepository.findOne(id), ReferenceStrategy.CONTENT).getCompany();
       return  Response.ok(apiBean).build();
    }
	
	@DELETE
	@ApiOperation(value= "Delete a Employee by its ID", response=EmployeeApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 404, message= "Employee has not been found"), 
        @ApiResponse(code = 204, message= "No content, employee is deleted"),
        @ApiResponse(code = 400, message= "BadInvalid employee identifier"),
        })
	public Response delete(@PathParam("uid") 
						   @ApiParam(value = "Unique identifier for the employee", required = true) 
						   Integer id) {
		if (companyRepository.exists(id)) {
			companyRepository.delete(id);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("The company '" + id + "' does not exist").build();
	}
	
	 @PUT
	 @ApiOperation(value= "Update existing employee", response=Response.class)
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.APPLICATION_JSON)
	 @ApiResponses({
	        @ApiResponse(code = 404, message= "The employee has not been found"), 
	        @ApiResponse(code = 202, message= "Accepted, employee has been updated") })
	 public Response update(
			 	@PathParam("uid")
	    		@ApiParam(value = "Unique identifier for the employee", required = true)
	    		Integer id, 
	    		
	    		@ApiParam(value = "Full object to be store as employee", required = true)
	    		EmployeeApiBean empApiBean) {
	    	
	    	if (employeeRepository.exists(id)) {
	    		employeeRepository.save(empApiBean.asEmployee());
	    		Response.accepted().entity("Employee has been updated").build();
	    	}
	        return Response.status(Response.Status.BAD_REQUEST).entity("ERROR").build();
	    }
	 
	 
}
