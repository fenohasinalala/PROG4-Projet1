package com.hei.project2p1.repository;

import com.hei.project2p1.exception.NotFoundException;
import com.hei.project2p1.model.Employee;
import com.hei.project2p1.repository.firm.EmployeeRepository;
import com.hei.project2p1.repository.firm.dao.EmployeeEntityDao;
import com.hei.project2p1.repository.firm.entity.EmployeeEntity;
import com.hei.project2p1.repository.firm.entity.validator.EmployeeEntityValidator;
import com.hei.project2p1.repository.firm.mapper.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class FirmEmployeeConnectorRepository implements EmployeeConnectorRepository {
    private EmployeeRepository repository;
    private final EmployeeEntityDao employeeDao;
    private final EmployeeMapper mapper;
    private final EmployeeEntityValidator validator;
    @Override
    public double count() {
        return repository.count();
    }

    @Override
    public Employee save(Employee toSave) {
        if (toSave.getId()!=null){
            Optional<EmployeeEntity> existingEmployee = repository.findById(toSave.getId());
            existingEmployee.ifPresent(present -> toSave.setEndToEndId(present.getEndToEndId()));
        }
        EmployeeEntity entityToSave = mapper.toEntity(toSave);
        validator.accept(entityToSave);
        return mapper.toDomain(
                repository.save(entityToSave));
    }


    @Override
    public Employee findById(String id) {
        EmployeeEntity employee = repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Employee with id "+id+" not found"));
        return mapper.toDomain(employee);
    }


    @Override
    public List<Employee> findByCriteria(String firstName, String lastName, String function, String countryCode,
                                         String gender, LocalDate entranceDateAfter, LocalDate entranceDateBefore,
                                         LocalDate leaveDateAfter, LocalDate leaveDateBefore, Pageable pageable) {
        List<EmployeeEntity> entityList = employeeDao.findByCriteria(
                firstName, lastName,function,countryCode, gender,
                entranceDateAfter, entranceDateBefore, leaveDateAfter, leaveDateBefore, pageable);
        return entityList.stream().map(mapper::toDomain).toList();
    }

    @Override
    public Employee addSupplementaryInformation(String id, Employee toSupply) {
        if (id!=null && !id.isEmpty()) {
            Optional<EmployeeEntity> employee = repository.findById(id);
            employee.ifPresent(toAdd -> {
                toSupply.setId(toAdd.getId());
                toSupply.setAddress(toAdd.getAddress());
                toSupply.setBirthDate(toAdd.getBirthDate());
                toSupply.setCinIssueDate(toAdd.getCinIssueDate());
                toSupply.setCinIssuePlace(toAdd.getCinIssuePlace());
                toSupply.setCinNumber(toAdd.getCinNumber());
                toSupply.setDepartureDate(toAdd.getDepartureDate());
                toSupply.setFirstName(toAdd.getFirstName());
                toSupply.setFunction(toAdd.getFunction());
                toSupply.setGender(Employee.Gender.valueOf(toAdd.getGender().toString()));
                toSupply.setHiringDate(toAdd.getHiringDate());
                toSupply.setLastName(toAdd.getLastName());
                toSupply.setNumberOfChildren(toAdd.getNumberOfChildren());
                toSupply.setPersonalEmail(toAdd.getPersonalEmail());
                toSupply.setPhoto(toAdd.getPhoto());
                toSupply.setProfessionalEmail(toAdd.getProfessionalEmail());
                toSupply.setRegistrationNo(toAdd.getRegistrationNo());
                toSupply.setSocioProfessionalCategory(Employee.SocioProfessionalCategory.valueOf(toAdd.getSocioProfessionalCategory().toString()));
            });
        }
        return toSupply;
    }

}
