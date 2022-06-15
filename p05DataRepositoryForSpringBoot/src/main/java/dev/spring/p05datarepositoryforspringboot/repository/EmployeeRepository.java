package dev.spring.p05datarepositoryforspringboot.repository;

import dev.spring.p05datarepositoryforspringboot.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

    // select * from employee where fullname='Ali';
    List<Employee> findByFullName(String s);
    List<Employee> findByFullNameContaining(String s);
    List<Employee> findByAgeGreaterThan(int age);
    List<Employee> findByAgeGreaterThanAndSalaryBefore(int age, double salary);
    List<Employee> findFirst3BySalaryGreaterThan(double salary);

    void deleteByFullName(String name);

    @Query("select count(e) from Employee e")
    int getNumberOfEmployees();

    @Query("select e.age, count(e.age) from Employee e GROUP BY e.age")
    List<?> getAgesWithGrouping();

    @Query(nativeQuery = true, value = "select age AS age, count(age) AS count from employee e GROUP BY age")
    List<EmployeeAgeStatistics> getAgesWithGroupingWithNativeQuery();
}
