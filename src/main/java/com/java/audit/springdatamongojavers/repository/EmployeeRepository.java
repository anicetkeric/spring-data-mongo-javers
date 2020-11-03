package com.java.audit.springdatamongojavers.repository;

import com.java.audit.springdatamongojavers.domain.Employee;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

@JaversSpringDataAuditable
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
