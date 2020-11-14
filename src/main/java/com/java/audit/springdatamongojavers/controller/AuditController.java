package com.java.audit.springdatamongojavers.controller;

import com.java.audit.springdatamongojavers.domain.Employee;
import org.javers.core.Changes;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.core.json.JsonConverter;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/audit")
public class AuditController {

    private final Javers javers;
    public AuditController(Javers javers) {
        this.javers = javers;
    }

    @GetMapping("/employee")
    public ResponseEntity<String> getEmployeeChanges() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Employee.class);

        List<Change> changes = javers.findChanges(jqlQuery.build());

        return ResponseEntity.ok().body(javers.getJsonConverter().toJson(changes));
    }


    @GetMapping("/employees")
    public String getEmployeeChanges2() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Employee.class)
                .withNewObjectChanges();

        Changes changes = javers.findChanges(jqlQuery.build());

        return "<pre>" + changes.prettyPrint() + "</pre>";
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeChanges(@PathVariable String id) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, Employee.class)
                .withNewObjectChanges();

        Changes changes = javers.findChanges(jqlQuery.build());

        return "<pre>" + changes.prettyPrint() + "</pre>";
    }

    @GetMapping("/employee/snapshots")
    public String getEmployeeSnapshots() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Employee.class);

        List<CdoSnapshot> changes = new ArrayList(javers.findSnapshots(jqlQuery.build()));

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return jsonConverter.toJson(changes);
    }

    @GetMapping("/employee/{login}/snapshots")
    public String getEmployeeSnapshots(@PathVariable String login) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId(login, Employee.class);

        List<CdoSnapshot> changes = javers.findSnapshots(jqlQuery.build());

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return jsonConverter.toJson(changes);
    }

}


