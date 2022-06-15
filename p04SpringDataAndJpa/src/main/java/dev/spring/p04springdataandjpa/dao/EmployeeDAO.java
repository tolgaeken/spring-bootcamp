package dev.spring.p04springdataandjpa.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO<Employee> extends BaseDAO<Employee> {
}
