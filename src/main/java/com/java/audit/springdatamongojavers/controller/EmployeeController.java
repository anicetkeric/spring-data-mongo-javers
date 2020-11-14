package com.java.audit.springdatamongojavers.controller;

import com.java.audit.springdatamongojavers.domain.Employee;
import com.java.audit.springdatamongojavers.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @GetMapping()
    public List<Employee> showNewEmployeeForm() {
        return employeeService.getAll();

    }

    @PostMapping()
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping(value = "/{id}")
    public Employee update(@PathVariable("id") String id, @RequestBody Employee employee) {

        Optional<Employee> emp = employeeService.getById(id);
        if (emp.isPresent()) {
            emp.get().setFirstName(employee.getFirstName());
            emp.get().setLastName(employee.getLastName());
            emp.get().setEmail(employee.getEmail());

            return employeeService.save(emp.get());
        }
        throw new RuntimeException("not found");

    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") String id) {
        return employeeService.getById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable(value = "id") String id) {
        employeeService.deleteById(id);
    }


}