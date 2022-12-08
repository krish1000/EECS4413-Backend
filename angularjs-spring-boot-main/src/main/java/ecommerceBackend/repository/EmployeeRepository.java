package ecommerceBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerceBackend.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}