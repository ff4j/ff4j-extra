package org.ff4j.springboot.sample.repository;

import org.ff4j.springboot.sample.entity.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository("showcase.repo.employee")
public interface EmployeeRepository  extends PagingAndSortingRepository<Employee, Integer> {}
