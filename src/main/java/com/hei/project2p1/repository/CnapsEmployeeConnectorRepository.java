package com.hei.project2p1.repository;

import com.hei.project2p1.repository.cnaps.entity.EmployeeCnapsEntity;
import com.hei.project2p1.repository.cnaps.EmployeeCnapsRepository;
import com.hei.project2p1.exception.NotFoundException;
import com.hei.project2p1.model.Employee;
import com.hei.project2p1.repository.firm.dao.EmployeeEntityDao;
import com.hei.project2p1.repository.firm.entity.EmployeeEntity;
import com.hei.project2p1.repository.firm.EmployeeRepository;
import com.hei.project2p1.repository.firm.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class CnapsEmployeeConnectorRepository implements EmployeeConnectorRepository {
    private EmployeeRepository mainRepository;
    private EmployeeCnapsRepository cnapsRepository;
    private final EmployeeEntityDao employeeDao;

    private final EmployeeMapper mapper;

    @Override
    public double count() {
        return mainRepository.count();
    }

    @Override
    public Employee save(Employee toSave) {
        if (toSave.getId()!=null){
            Optional<EmployeeEntity> existingEmployee = mainRepository.findById(toSave.getId());
            existingEmployee.ifPresent(present -> toSave.setEndToEndId(present.getEndToEndId()));
        }
        EmployeeEntity entityToSave = mapper.toEntity(toSave);
        return mapper.toDomain(
                mainRepository.save(entityToSave));
    }


    @Override
    public Employee findById(String id) {

        EmployeeEntity employee = mainRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Employee with id "+id+" not found"));
        Employee toDisplay = mapper.toDomain(employee);
        return setCnapsNumber(employee.getEndToEndId(),toDisplay);
    }


    @Override
    public List<Employee> findByCriteria(String firstName, String lastName, String function, String countryCode,
                                         String gender, LocalDate entranceDateAfter, LocalDate entranceDateBefore,
                                         LocalDate leaveDateAfter, LocalDate leaveDateBefore, Pageable pageable) {
        List<EmployeeEntity> entityList = employeeDao.findByCriteria(
                firstName, lastName,function,countryCode, gender,
                entranceDateAfter, entranceDateBefore, leaveDateAfter, leaveDateBefore, pageable);
                //.stream().map(mapper::toDomain).toList();
        List<Employee> toDisplay = new ArrayList<>();
        for (EmployeeEntity e:entityList) {
            toDisplay.add(setCnapsNumber(e.getEndToEndId(),mapper.toDomain(e)));
        }
        return toDisplay;
    }

    @Override
    public Employee addSupplementaryInformation(String id, Employee toSupply) {
        return null;
    }

    private Employee setCnapsNumber(String employeeCnapsId, Employee employee){
        if (employeeCnapsId != null && !employeeCnapsId.isEmpty()) {
            Optional<EmployeeCnapsEntity> employeeCnaps = cnapsRepository.findById(employeeCnapsId);
            employee.setCnapsNumber(employeeCnaps.map(EmployeeCnapsEntity::getCnapsNumber).orElse(null));
        }
        return employee;
    }

}
