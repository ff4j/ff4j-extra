package org.ff4j.springboot.sample.apibean;

import java.util.List;

import org.ff4j.springboot.sample.resources.BaseHrResource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;

/**
 * This bean is created to extend the {@link PageImpl} and provide 'previous' and 'next' links.
 * 
 * @author Digital Transformation LAB, Stream SOA
 */
@JsonIgnoreProperties			// You should not raise error is field not found
@JsonInclude(Include.NON_NULL)	// You should not display null values
public class CompanyApiBeanPage extends PageImpl <CompanyApiBean>{

	@JsonIgnore
	private static final long serialVersionUID = -5804841108899271272L;
	
	@ApiModelProperty(value = "Links to previous page if exist")
	private String previousPage = null;
	
	@ApiModelProperty(value = "Links to next page if exist")
	private String nextPage = null;
	
	public CompanyApiBeanPage(List<CompanyApiBean> content, Pageable pageable, long total) {
		super(content, pageable, total);
		StringBuilder sb = new StringBuilder(JaxRsLinkBuilder.linkTo(BaseHrResource.class).slash("companiesPageable").toString());
		sb.append("?size=" + pageable.getPageSize());
		
		Order o = pageable.getSort().iterator().next();
		sb.append("&sort=" + o.getProperty());
		sb.append("&direction=" + o.getDirection());
		
		if (!isFirst()) {
			previousPage = sb.toString() + "&page=" + (pageable.getPageNumber() - 1);
		}
		if (!isLast()) {
			nextPage = sb.toString() + "&page=" + (pageable.getPageNumber() + 1);
		}
	}

	/**
	 * Getter accessor for attribute 'previousPage'.
	 *
	 * @return
	 *       current value of 'previousPage'
	 */
	public String getPreviousPage() {
		return previousPage;
	}

	/**
	 * Setter accessor for attribute 'previousPage'.
	 * @param previousPage
	 * 		new value for 'previousPage '
	 */
	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

	/**
	 * Getter accessor for attribute 'nextPage'.
	 *
	 * @return
	 *       current value of 'nextPage'
	 */
	public String getNextPage() {
		return nextPage;
	}

	/**
	 * Setter accessor for attribute 'nextPage'.
	 * @param nextPage
	 * 		new value for 'nextPage '
	 */
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	

}
