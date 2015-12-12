package org.ff4j.springboot.utils;

/**
 * REST resource should not use enums are parameters yet interesting in services. This utility
 * class try to automatically convert string expression to relative enum. If the value is
 * incorrect an IllegalArgumentException is raised (catch and convert to HTTP 400 by {@link IllegalArgumentExceptionMapper}.
 *  
 * <code>
 *  ReferenceStrategy strategy = ApiUtils.getEnumFromString(ReferenceStrategy.class, employees);
 * </code>
 * 
 * @author Digital Transformation LAB, Stream SOA
 */
public class ApiUtils {
	
	/**
	 * Hide constructor.
	 */
	private ApiUtils() {}
	
	/**
	 * Convert an String into its relative Enum value (if possible)
	 *
	 * @param enumClass
	 * 		current enum class
	 * @param value
	 * 		current String value
	 * @return
	 * 		the Enum value if exit
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> T getEnumFromString(Class<T> enumClass, String value) {
		if (enumClass == null) {
			throw new IllegalArgumentException("The enum class is required");
		}
		
		for(Enum<?> enumValue : enumClass.getEnumConstants()) {
			if (enumValue.toString().equalsIgnoreCase(value)) {
				return (T) enumValue;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for(Enum<?> enumValue : enumClass.getEnumConstants()) {
			sb.append(first ? "" : ",").append(enumValue);
			first = false;
		}
		
		throw new IllegalArgumentException(value + " is invalid, valids are " + sb.toString());
	}

}
