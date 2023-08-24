package com.hei.project2p1.repository;

import com.hei.project2p1.model.Employee;
import com.hei.project2p1.repository.cnaps.EmployeeCnapsRepository;
import com.hei.project2p1.repository.cnaps.entity.EmployeeCnapsEntity;
import com.hei.project2p1.repository.cnaps.mapper.EmployeeCnapsMapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@AllArgsConstructor
public class CnapsEmployeeConnectorRepository implements EmployeeConnectorRepository {
    private EmployeeCnapsRepository repository;
    private EmployeeCnapsMapper mapper;
    private static final String UNSUPPORTED_ERROR_MESSAGE = "Unsupported: only getting methods are!";

    @Override
    public double count() {
        throw new NotImplementedException(UNSUPPORTED_ERROR_MESSAGE);
    }

    @Override
    public Employee save(Employee toSave) {
        throw new NotImplementedException(UNSUPPORTED_ERROR_MESSAGE);    }


    @Override
    public Employee findById(String id) {
        EmployeeCnapsEntity employee = repository.findById(id).orElse(null);
        if (employee==null){
            return null;
        }
        return mapper.toDomain(employee);
    }

    @Override
    public List<Employee> findByCriteria(String firstName, String lastName, String function, String countryCode,
                                         String gender, LocalDate entranceDateAfter, LocalDate entranceDateBefore,
                                         LocalDate leaveDateAfter, LocalDate leaveDateBefore, Pageable pageable) {
        throw new NotImplementedException(UNSUPPORTED_ERROR_MESSAGE);
    }

    @Override
    public Employee addSupplementaryInformation(String id, Employee toSupply) {
        throw new NotImplementedException(UNSUPPORTED_ERROR_MESSAGE);
    }
}
