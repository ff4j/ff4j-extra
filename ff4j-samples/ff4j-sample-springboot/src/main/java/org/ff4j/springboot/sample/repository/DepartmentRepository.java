package org.ff4j.springboot.sample.repository;

import org.ff4j.springboot.sample.entity.Department;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("showcase.repo.department")
public interface DepartmentRepository  extends PagingAndSortingRepository<Department, Integer> {}
