package org.ff4j.springboot.sample.repository;

import org.ff4j.springboot.sample.entity.Company;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Implementing CRUD Operations for {@link Company}.
 */
@Repository("showcase.repo.company")
public interface CompanyRepository extends PagingAndSortingRepository<Company , Integer> {}
