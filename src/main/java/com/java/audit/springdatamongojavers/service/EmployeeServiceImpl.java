package com.java.audit.springdatamongojavers.service;

import com.java.audit.springdatamongojavers.domain.Employee;
import com.java.audit.springdatamongojavers.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getById(String id) {
        return employeeRepository.findById(id);
    }

    @Override
    public void deleteById(String id) {
        this.employeeRepository.deleteById(id);
    }



}