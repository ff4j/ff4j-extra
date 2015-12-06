package org.ff4j.springboot.hateoas;

import org.springframework.hateoas.ResourceSupport;

/**
 * Exposition of entity as HateOas resource.
 *
 * @param <ENTITY>
 * 		current entity to be expose as resource HateOAS.
 */
public class HateoasEntityResource<ENTITY> extends ResourceSupport {
	
	/** Current entity to be filled. */
	private ENTITY content;

	/**
	 * HateOAS.
	 */
	public HateoasEntityResource() {
	}
	
	/**
	 * Parameterized constructor.
	 *
	 * @param ent
	 * 		current entity
	 */
	public HateoasEntityResource(ENTITY ent) {
		this.content = ent;
	}
	
	/**
	 * Getter accessor for attribute 'content'.
	 *
	 * @return
	 *       current value of 'content'
	 */
	public ENTITY getContent() {
		return content;
	}

	/**
	 * Setter accessor for attribute 'content'.
	 * @param content
	 * 		new value for 'content '
	 */
	public void setContent(ENTITY content) {
		this.content = content;
	}


}
