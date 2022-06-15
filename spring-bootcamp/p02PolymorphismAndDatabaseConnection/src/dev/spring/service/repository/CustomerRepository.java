package dev.spring.service.repository;

import dev.spring.models.Vehicle;

import java.util.List;

public interface CustomerRepository {
    void deleteCustomerFromDatabase(long ssid);
    List<Vehicle> findVehiclesOfCustomer(long ssid);


}
