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

import org.ff4j.springboot.hateoas.HateoasEntityResource;
import org.ff4j.springboot.hateoas.JaxRsResourceAssemblerSupport;
import org.ff4j.springboot.sample.apibean.CompanyApiBean;
import org.ff4j.springboot.sample.apibean.DepartmentApiBean;
import org.ff4j.springboot.sample.apibean.ReferenceStrategy;
import org.ff4j.springboot.utils.ApiUtils;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@Path("/v1/companies/{uid}")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/v1/companies/{uid}", description = "CRUD Operations on the entity <b>Company</b>")
public class CompanyResource extends AbstractHrResource {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Read information about a Company", response=CompanyApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Information about Company"), 
        @ApiResponse(code = 404, message= "Company not found") })
    public Response read(
    		
    		@PathParam("uid") 
    		@ApiParam(value = "Unique identifier for the company", required = true) 
    		Integer id,
    		
    		@QueryParam("employees") @DefaultValue("LINK")
    		@ApiParam(value = "Strategy to list employees", required = false, defaultValue= "LINK", allowableValues="NONE,LINK,CONTENT,FULL")
    		String employees,

    		@QueryParam("departments") @DefaultValue("LINK")
    		@ApiParam(value = "Strategy to list employees", required = false, defaultValue= "LINK", allowableValues="NONE,LINK,CONTENT,FULL")
    		String departments) {
       
	   // Convert String parameter into target Enum and raise a 400 BAD REQUEST if not valid
	   ReferenceStrategy strategyEmp = ApiUtils.getEnumFromString(ReferenceStrategy.class, employees);
	   
	   // Convert String parameter into target Enum and raise a 400 BAD REQUEST if not valid
	   ReferenceStrategy strategyDep = ApiUtils.getEnumFromString(ReferenceStrategy.class, employees);
			
       // Check existence, eventually return 404
       if (!companyRepository.exists(id)) {
    	   return Response.status(Response.Status.NOT_FOUND).
    			   entity("HTTP 404 (NOT FOUND) - The company '" + id + "' does not exist").build();
       }
       
       // Load and return 200 if ok (500 is erroroccured)
       return Response.ok(new CompanyApiBean(companyRepository.findOne(id), strategyEmp, strategyDep)).build();
    }
	
	@GET
	@Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Read employees about a Company", response=CompanyApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Employees of the Company"), 
        @ApiResponse(code = 404, message= "Company not found") })
    public Response readEmployees(
    			@PathParam("uid") 
    			@ApiParam(value = "Unique identifier for the company", required = true) 
    			Integer id) {
		 // Check existence, eventually return 404
	     if (!companyRepository.exists(id)) {
	    	return Response.status(Response.Status.NOT_FOUND).entity("HTTP 404 (NOT FOUND) - The company '" + id + "' does not exist").build();
	     }
	     
	     CompanyApiBean apiBean = new CompanyApiBean(companyRepository.findOne(id), 
	    		 ReferenceStrategy.CONTENT, ReferenceStrategy.NONE);
	     
	     return Response.ok(apiBean.getEmployees()).build();
	}
	
	@GET
	@Path("/departments")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value= "Read departments about a Company", response=DepartmentApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 200, message= "Departments of the Company"), 
        @ApiResponse(code = 404, message= "Company not found") })
    public Response readDepartments(
    			@PathParam("uid") 
    			@ApiParam(value = "Unique identifier for the company", required = true) 
    			Integer id) {
		 // Check existence, eventually return 404
	     if (!companyRepository.exists(id)) {
	    	return Response.status(Response.Status.NOT_FOUND).entity("HTTP 404 (NOT FOUND) - The company '" + id + "' does not exist").build();
	     }
	     CompanyApiBean apiBean = new CompanyApiBean(companyRepository.findOne(id), 
	    		 ReferenceStrategy.NONE, ReferenceStrategy.CONTENT);
	     
	     return Response.ok(apiBean.getDepartments()).build();
	}
	
	@DELETE
	@ApiOperation(value= "Delete a Company by its ID", response=CompanyApiBean.class)
    @ApiResponses({
        @ApiResponse(code = 404, message= "Company has not been found"), 
        @ApiResponse(code = 204, message= "No content, company is deleted"),
        @ApiResponse(code = 400, message= "BadInvalid company identifier"),
        })
	public Response delete(@PathParam("uid") @ApiParam(value = "Unique identifier for the company", required = true) Integer id) {
		if (companyRepository.exists(id)) {
			companyRepository.delete(id);
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND).entity("The company '" + id + "' does not exist").build();
	}
	
    @PUT
    @ApiOperation(value= "Update existing company", response=Response.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiResponses({
        @ApiResponse(code = 404, message= "The company has not been found"), 
        @ApiResponse(code = 202, message= "Accepted, Company has been updated") })
    public Response updateCompany(
    		@PathParam("uid")
    		@ApiParam(value = "Unique identifier for the company", required = true)
    		Integer id, 
    		
    		@ApiParam(value = "Full object to be store as company", required = true)
    		CompanyApiBean compApiBean) {
    	
    	if (companyRepository.exists(id)) {
    		companyRepository.save(compApiBean.asCompany());
    		Response.accepted().entity("Company has been updated").build();
    	}
        return Response.status(Response.Status.BAD_REQUEST).entity("ERROR").build();
    }
    
    
    
    // ---------- Illustrating dynamic HateOAS --------------
    
    @GET
	@Path("/hateoas")
	public Response findOneAsHateOas(@PathParam("uid") Integer id) {
    	CompanyApiBean result = new CompanyApiBean(companyRepository.findOne(id), ReferenceStrategy.NONE, ReferenceStrategy.NONE);
		return Response.ok(hateoas.toResource(result)).build();
	}
    
    protected CompanyRAS hateoas = new CompanyRAS(getClass(), CompanyHateOAS.class);
    
    public static class CompanyHateOAS extends HateoasEntityResource<CompanyApiBean> {};
    
    public static class CompanyRAS extends JaxRsResourceAssemblerSupport<CompanyApiBean, CompanyHateOAS> {
    	public CompanyRAS(Class<?> controllerClass, Class<CompanyHateOAS> resourceType) {
    		super(controllerClass, resourceType);
    	}
    	public CompanyHateOAS toResource(CompanyApiBean cab) {
    		CompanyHateOAS th = createResourceWithId(cab.getIdCompany(), cab, cab.getIdCompany());
    		th.setContent(cab);
    		return th;
    	}
    }
    
    
	
}
