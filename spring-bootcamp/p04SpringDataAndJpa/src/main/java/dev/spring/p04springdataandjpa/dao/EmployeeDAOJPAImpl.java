package dev.spring.p04springdataandjpa.dao;

import dev.spring.p04springdataandjpa.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class EmployeeDAOJPAImpl implements EmployeeDAO<Employee> {

    private EntityManager entityManager;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeDAOJPAImpl.class);

    public EmployeeDAOJPAImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        return entityManager.createQuery("FROM Employee e",Employee.class).getResultList();

    }

    @Override
    public Employee findById(int id) {

        return entityManager.find(Employee.class,id);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return (Employee) entityManager.merge(employee);
    }

    @Override
    public void deleteById(int id) {
        Employee employee = this.findById(id);

        if(employee == null){
            logger.error("There is no employee with id : " + id);
        }

        entityManager.remove(employee);
    }

    @Override
    public Employee update(Employee employee) {
        return entityManager.merge(employee);
    }
}
