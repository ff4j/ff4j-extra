package org.ff4j.springboot.hateoas;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;

/**
 * Proposition of super class to ease HateOAS representation of resources.
 *
 * <br/>Another solution could be to work with 'EntityLinks' in spring-plugin-core:
 * <code>
 *  Link selfRel = entityLinks.linkToSingleResource(MyClass.class, myObject.getId()).withSelfRel();
 * </code>
 * 
 * @param <T>
 * 		entity to be distributed
 */
public class AbstractHateOasResource <ENTITY> {
	
	@Context
	protected UriInfo uriInfo;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected JaxRsResourceAssembler<ENTITY> hateoas = 
			new JaxRsResourceAssembler(getClass(), HateoasEntityResource.class);

	/**
	 * Transform output to hateoas.
	 * @param result
	 * 		current result
	 * @return
	 * 		new result
	 */
	protected Response toHateOas(ENTITY result) {
		return Response.ok(hateoas.toResource(result)).build();
	}
	
	/**
	 * Transform output to hateoas.
	 * @param result
	 * 		current result
	 * @return
	 * 		new result
	 */
	protected Response toHateOas(Iterable<ENTITY> result) {
		Link self = JaxRsLinkBuilder.linkTo(getClass()).withSelfRel();
		return Response.ok(new Resources<>(hateoas.toResources(result),self)).build();
	}

}
