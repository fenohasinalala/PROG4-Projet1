package com.hei.project2p1.repository;

import com.hei.project2p1.model.Employee;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeConnectorRepository {
    double count();

    Employee save(Employee toSave);

    Employee findById(String id);
    List<Employee> findByCriteria(String firstName, String lastName, String function,
                                  String countryCode,
                                  String gender, LocalDate entranceDateAfter, LocalDate entranceDateBefore,
                                  LocalDate leaveDateAfter, LocalDate leaveDateBefore,
                                  Pageable pageable);

    Employee addSupplementaryInformation(String id, Employee toSupply);

}
