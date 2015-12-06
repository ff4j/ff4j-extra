package org.ff4j.springboot.hateoas;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

import javax.persistence.Id;

/**
 * Default implementation of an assembler. Expected to found a readable property with @Id Annotation.
 *
 * @param <ENTITY>
 * 		current entity to work withé
 */
public class JaxRsResourceAssembler <ENTITY> extends JaxRsResourceAssemblerSupport<ENTITY, HateoasEntityResource<ENTITY>>{
	
	/**
	 * Default constructor.
	 *
	 * @param controllerClass
	 * 		current controller claase
	 * @param resourceType
	 * 		ressource to be filled
	 */
	public JaxRsResourceAssembler(Class<?> controllerClass, Class<HateoasEntityResource<ENTITY>> resourceType) {
		super(controllerClass, resourceType);
	}

	/** {@inheritDoc} */
	@Override
	public HateoasEntityResource<ENTITY> toResource(ENTITY entity) {
		Optional <Field > fieldId = 
				// List fields
				Arrays.asList(entity.getClass().getDeclaredFields()).stream().
				// Find the one annotated with id.
				filter(f -> f.isAnnotationPresent(Id.class)).findFirst();
		
		if (!fieldId.isPresent()) {
			throw new IllegalArgumentException("Expecting annotation @Id on some field to pass within URL");
		}
		try {
			Object id = getFieldValueDynamically(fieldId.get().getName(), entity);
			HateoasEntityResource<ENTITY> resource = createResourceWithId(id, entity);
			resource.setContent(entity);
	        return resource;
		} catch (Exception e) {
			throw new IllegalArgumentException("Cannot introspect object");
		}
		
	}
	
	/**
	 * Retrieve field information dynamically.
	 *
	 * @param fieldName
	 * 		current field name
	 * @param o
	 * 		current object
	 * @return
	 * @throws Exception
	 */
	public Object getFieldValueDynamically(String fieldName, final Object o)
	throws Exception{
		BeanInfo bi = Introspector.getBeanInfo(o.getClass());
		
		// Get properties information and convert into LIST 
		return Arrays.asList(bi.getPropertyDescriptors()).stream().
			// Stream, filter only the spotted property
			filter(pd-> (pd.getReadMethod() != null && fieldName.equals(pd.getName()))).
			// Extract the correct 'getter' and invoke it
			findFirst().get().getReadMethod().invoke(o);
	}

}
