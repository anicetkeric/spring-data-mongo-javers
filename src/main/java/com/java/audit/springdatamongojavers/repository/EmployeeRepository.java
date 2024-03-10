package com.java.audit.springdatamongojavers.repository;

import com.java.audit.springdatamongojavers.domain.Employee;
import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
@JaversSpringDataAuditable
public interface EmployeeRepository extends MongoRepository<Employee, String> {
}

