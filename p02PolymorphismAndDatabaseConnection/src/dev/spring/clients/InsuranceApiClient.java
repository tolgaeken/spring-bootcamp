package dev.spring.clients;

import dev.spring.controller.CustomerController;
import dev.spring.models.*;
import dev.spring.utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class InsuranceApiClient {
    public static void main(String[] args) {

        if(checkTestData() == 0){
            saveTestData();
        }

        CustomerController controller = new CustomerController();

        /*
            Tested saving new customer
         */
        // Customer customer4 = new Customer("Aleyna Kütük", "Istanbul", 6452545241L );
        // controller.saveCustomer(customer4);

        /*
            Tested delete a customer
         */
        // controller.deleteCustomer(6452545241L);

        /*
            Tested finding all customer
         */
        List<Customer> returnedList = controller.findAllCustomers();
        for (Customer customer : returnedList) {
            System.out.println(customer);
        }

         /*
            Tested finding all vehicles of a customer
         */
        System.out.println("Vehicles of customer : " );
        List<Vehicle> vehicleList = controller.findVehiclesOfCustomer(321562365123L);

        for (int i = 0; i < vehicleList.size(); i++) {
            System.out.println((i+1) + " --> " + vehicleList.get(i).getV_model());
        }
        // controller.findVehiclesOfCustomer(321562365123L).stream().forEach(System.out::println);

        System.exit(0);

    }

    private static int checkTestData() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        return em.createQuery("from Customer", Customer.class).getResultList().size();
    }


    private static void saveTestData() {

        Customer customer1 = new Customer("Tolga Eken", "Ankara", 321562365123L );
        Customer customer2 = new Customer("Ali Veli", "Istanbul", 32132143246L );
        Customer customer3 = new Customer("Ayse Simsek", "Edirne", 6452533241L );

        Vehicle car1 = new Car(2020, "Porsche Cayenne", "34VG5677", "Red");
        Vehicle car2 = new Car(2017, "Toyota Corolla", "08HG234", "Black");
        Vehicle moto1 = new Motorcycle(2020, "Suzuki", "65NN322", 120.0);
        Vehicle moto2 = new Motorcycle(2017, "Yamaha", "08TT122", 340.0);
        Vehicle moto3 = new Motorcycle(2013, "Harley Davidson", "34VG111", 420.0);

        car1.setCustomer(customer1);
        car2.setCustomer(customer2);
        moto1.setCustomer(customer3);
        moto2.setCustomer(customer3);
        moto3.setCustomer(customer1);

        Accident accident1 = new Accident(LocalDate.of(2021, Month.AUGUST,12));
        Accident accident2 = new Accident(LocalDate.of(2020, Month.JUNE,24));
        Accident accident3 = new Accident(LocalDate.of(2019, Month.MARCH,4));

        car1.getAccidents().add(accident1);
        car2.getAccidents().add(accident1);
        moto1.getAccidents().add(accident2);
        moto1.getAccidents().add(accident3);
        moto3.getAccidents().add(accident2);

        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");

        try {
            em.getTransaction().begin();

            em.persist(car1);
            em.persist(car2);
            em.persist(moto1);
            em.persist(moto2);
            em.persist(moto3);

            em.persist(customer1);
            em.persist(customer2);
            em.persist(customer3);

            em.persist(accident1);
            em.persist(accident2);
            em.persist(accident3);

            em.getTransaction().commit();

            System.out.println("All data persisted...");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }
    }
}
