package dev.spring.controller;

import dev.spring.models.Customer;
import dev.spring.models.Vehicle;
import dev.spring.service.CustomerService;

import java.util.List;

public class CustomerController {

    private CustomerService customerService = new CustomerService();

    public Customer findCustomer(int custId){
        return customerService.findById(custId);
    }

    public List<Customer> findAllCustomers(){
        return customerService.findAll();
    }

    public void saveCustomer(Customer customer){
        customerService.saveToDatabase(customer);
    }


    public void deleteCustomer(long ssid){
        customerService.deleteCustomerFromDatabase(ssid);
    }

    public void updateCustomer(Customer customer, int id){
        customerService.updateOnDatabase(customer, id);
    }

    public List<Vehicle> findVehiclesOfCustomer(long ssid){
        return customerService.findVehiclesOfCustomer(ssid);
    }

}
