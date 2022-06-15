package dev.spring.p04springdataandjpa.service;

import dev.spring.p04springdataandjpa.dao.EmployeeDAO;
import dev.spring.p04springdataandjpa.model.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements BaseService<Employee>{

    private EmployeeDAO employeeDAO;


    public EmployeeService(@Qualifier("employeeDAOJPAImpl") EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return (Employee) employeeDAO.findById(id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return (Employee) employeeDAO.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public Employee update(Employee employee) {
        return (Employee) employeeDAO.update(employee);
    }
}
