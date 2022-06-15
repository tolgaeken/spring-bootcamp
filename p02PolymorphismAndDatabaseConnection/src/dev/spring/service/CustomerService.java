package dev.spring.service;

import dev.spring.models.Customer;
import dev.spring.models.Vehicle;
import dev.spring.service.repository.CrudRepository;
import dev.spring.service.repository.CustomerRepository;
import dev.spring.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.util.List;

public class CustomerService implements CrudRepository<Customer>, CustomerRepository {

    EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

    @Override
    public List<Customer> findAll() {
        return em.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer findById(int id) {
        return  em.find(Customer.class, id);
    }

    @Override
    public void saveToDatabase(Customer customer) {
        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void deleteFromDatabase(Customer customer) {
        try {
            em.getTransaction().begin();

            Customer foundCustomer = em.createQuery("from Customer c WHERE c.ssid =:ssid", Customer.class).setParameter("ssid", customer.getSsid()).getSingleResult();
            em.remove(foundCustomer);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void deleteFromDatabase(int id) {
        try {
            em.getTransaction().begin();

            Customer foundCustomer = em.find(Customer.class, id);
            em.remove(foundCustomer);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void updateOnDatabase(Customer customer, int id) {
        try {
            em.getTransaction().begin();

            Customer foundCustomer = em.find(Customer.class, id);
            foundCustomer.setAddress(customer.getAddress());
            foundCustomer.setName(customer.getName());
            foundCustomer.setSsid(customer.getSsid());
            em.merge(foundCustomer);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public void deleteCustomerFromDatabase(long ssid) {
        try {
            em.getTransaction().begin();

            Customer customer = em.createQuery("from Customer c WHERE c.ssid =:ssid", Customer.class).setParameter("ssid", ssid).getSingleResult();
            em.remove(customer);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }

    @Override
    public List<Vehicle> findVehiclesOfCustomer(long ssid) {
        return findCustomerBySSID(ssid).getVehicleList();
    }

    public Customer findCustomerBySSID(long ssid){
        return em.createQuery("from Customer c where c.ssid=:custSsid", Customer.class).setParameter("custSsid", ssid).getSingleResult();
    }
}
