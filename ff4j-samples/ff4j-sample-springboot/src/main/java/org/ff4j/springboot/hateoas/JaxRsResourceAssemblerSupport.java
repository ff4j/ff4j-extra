package org.ff4j.springboot.hateoas;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.util.Assert;

/**
 * Define link for each resource to be used.
 *
 * @param <BEAN>
 * 		current bean class
 * @param <RSC>
 * 		current bean as hateOAD resource
 */
public abstract class JaxRsResourceAssemblerSupport<BEAN, RSC extends ResourceSupport> extends ResourceAssemblerSupport<BEAN, RSC> {

	/** Current Controller class. */
	private final Class<?> controllerClass;

	/**
	 * Constructor to work with resources.
	 *
	 * @param controllerClass
	 * @param resourceType
	 */
	public JaxRsResourceAssemblerSupport(Class<?> controllerClass, Class<RSC> resourceType) {
		super(controllerClass, resourceType);
		this.controllerClass = controllerClass;
	}

	@Override
	protected RSC createResourceWithId(Object id, BEAN entity, Object... parameters) {
		Assert.notNull(entity);
		Assert.notNull(id);
		RSC instance = instantiateResource(entity);
		instance.add(JaxRsLinkBuilder.linkTo(controllerClass, parameters).slash(id).withSelfRel());
		return instance;
	}
}