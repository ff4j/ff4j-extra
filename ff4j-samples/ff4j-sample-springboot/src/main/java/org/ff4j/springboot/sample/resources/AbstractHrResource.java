package org.ff4j.springboot.sample.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.ff4j.springboot.sample.repository.CompanyRepository;
import org.ff4j.springboot.sample.repository.DepartmentRepository;
import org.ff4j.springboot.sample.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Utility Class to easy REST definitiotn
 * 
 * @author Digital Transformation LAB, Stream SOA
 */
public class AbstractHrResource {

	@Context
	protected UriInfo uriInfo;
	
	@Context
	protected Request request;
	    
	@Context
	protected SecurityContext securityContext;
	
	@Autowired
	protected CompanyRepository companyRepository;
	
	@Autowired
	protected EmployeeRepository employeeRepository;
	
	@Autowired
	protected DepartmentRepository departementRepository;
	

}
