package com.java.audit.springdatamongojavers.service;


import com.java.audit.springdatamongojavers.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();

    Employee save(Employee employee);

    Optional<Employee> getById(String id);

    void deleteById(String id);
}