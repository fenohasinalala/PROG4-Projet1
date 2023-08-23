package com.hei.project2p1.repository;

import com.hei.project2p1.cnaps.repository.EmployeeCnapsRepository;
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

    private final FirmEmployeeConnectorRepository firmRepository;
    private final EmployeeCnapsRepository cnapsRepository;

    private EmployeeConnectorRepository getCombineRepository(){
        //Main repository (to be specified in the supplementary repository): firmRepository
        //Supplementary repository: [CnapsEmployeeConnectorRepository]
        return new CnapsEmployeeConnectorRepository(firmRepository,cnapsRepository);
    }



    @Override
    public double count() {
        return getCombineRepository().count();
    }

    @Override
    public Employee save(Employee toSave) {
        return getCombineRepository().save(toSave);
    }

    @Override
    public Employee findById(String id) {
        return getCombineRepository().findById(id);
    }

    @Override
    public List<Employee> findByCriteria(String firstName, String lastName, String function, String countryCode, String gender, LocalDate entranceDateAfter, LocalDate entranceDateBefore, LocalDate leaveDateAfter, LocalDate leaveDateBefore, Pageable pageable) {
        return getCombineRepository().findByCriteria(
                firstName, lastName,function,countryCode, gender,
                entranceDateAfter, entranceDateBefore, leaveDateAfter, leaveDateBefore, pageable);
    }

    @Override
    public Employee addSupplementaryInformation(String id, Employee toSupply) {
        return getCombineRepository().addSupplementaryInformation(id, toSupply);
    }
}
