package com.hei.project2p1.repository;

import com.hei.project2p1.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
@Primary
public class EmployeeConnectorRepositoryFacade implements EmployeeConnectorRepository {


    @Override
    public double count() {
        return 0;
    }

    @Override
    public Employee save(Employee toSave) {
        return null;
    }

    @Override
    public Employee findById(String id) {
        return null;
    }

    @Override
    public List<Employee> findByCriteria(String firstName, String lastName, String function, String countryCode, String gender, LocalDate entranceDateAfter, LocalDate entranceDateBefore, LocalDate leaveDateAfter, LocalDate leaveDateBefore, Pageable pageable) {
        return null;
    }

    @Override
    public Employee addSupplementaryInformation(String id, Employee toSupply) {
        return null;
    }
}
