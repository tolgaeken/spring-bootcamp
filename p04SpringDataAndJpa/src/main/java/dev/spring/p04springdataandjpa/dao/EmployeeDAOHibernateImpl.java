package dev.spring.p04springdataandjpa.dao;

import dev.spring.p04springdataandjpa.model.Employee;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class EmployeeDAOHibernateImpl  implements EmployeeDAO<Employee> {

    private EntityManager entityManager;

    public EmployeeDAOHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from Employee",Employee.class).getResultList();
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public Employee save(Employee employee) {
        Session session = entityManager.unwrap(Session.class);
        return (Employee) session.merge(employee);
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Employee update(Employee object) {
        return null;
    }
}
