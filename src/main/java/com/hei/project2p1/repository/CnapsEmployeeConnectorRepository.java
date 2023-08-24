package com.hei.project2p1.repository;

import com.hei.project2p1.repository.cnaps.entity.EmployeeCnapsEntity;
import com.hei.project2p1.repository.cnaps.EmployeeCnapsRepository;
import com.hei.project2p1.model.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CnapsEmployeeConnectorRepository implements EmployeeConnectorRepository {
    private EmployeeConnectorRepository toComplete;
    private EmployeeCnapsRepository mainRepository;

    @Override
    public double count() {
        return toComplete.count();
    }

    @Override
    public Employee save(Employee toSave) {
        return toComplete.save(toSave);
    }


    @Override
    public Employee findById(String id) {
        Employee employee = toComplete.findById(id);
        return addSupplementaryInformation(employee.getEndToEndId(),employee);
    }

    @Override
    public List<Employee> findByCriteria(String firstName, String lastName, String function, String countryCode,
                                         String gender, LocalDate entranceDateAfter, LocalDate entranceDateBefore,
                                         LocalDate leaveDateAfter, LocalDate leaveDateBefore, Pageable pageable) {
        List<Employee> toDisplay = toComplete.findByCriteria(
                firstName, lastName,function,countryCode, gender,
                entranceDateAfter, entranceDateBefore, leaveDateAfter, leaveDateBefore, pageable);
        return toDisplay.stream().map(e->addSupplementaryInformation(e.getEndToEndId(),e)).toList();
    }

    @Override
    public Employee addSupplementaryInformation(String id, Employee toSupply) {
        if (id!=null && !id.isEmpty()){
            Optional<EmployeeCnapsEntity> employee = mainRepository.findById(id);
            employee.ifPresent(toAdd -> {
                toSupply.setCnapsNumber(toAdd.getCnapsNumber());
            });
        }
        return toSupply;
    }

}
