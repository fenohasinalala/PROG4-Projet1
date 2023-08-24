package com.hei.project2p1.repository.cnaps.mapper;

import com.hei.project2p1.model.Employee;
import com.hei.project2p1.repository.cnaps.entity.EmployeeCnapsEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeCnapsMapper {

    public Employee toDomain(EmployeeCnapsEntity entity) {
        return Employee.builder()
                .id(entity.getId())
                .registrationNo(entity.getRegistrationNo())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .gender(entity.getGender()==null?null:Employee.Gender.valueOf(entity.getGender().toString()))
                .phones(List.of())
                .address(entity.getAddress())
                .personalEmail(entity.getPersonalEmail())
                .professionalEmail(entity.getProfessionalEmail())
                .cinNumber(entity.getCinNumber())
                .cinIssueDate(entity.getCinIssueDate())
                .cinIssuePlace(entity.getCinIssuePlace())
                .function(entity.getFunction())
                .numberOfChildren(entity.getNumberOfChildren())
                .hiringDate(entity.getHiringDate())
                .departureDate(entity.getDepartureDate())
                .socioProfessionalCategory(entity.getSocioProfessionalCategory()==null?null:Employee.SocioProfessionalCategory.valueOf(entity.getSocioProfessionalCategory().toString()))
                .cnapsNumber(entity.getCnapsNumber())
                .photo(entity.getPhoto())
                .build();
    }

    public EmployeeCnapsEntity toEntity(Employee domain) {
        return EmployeeCnapsEntity.builder()
                .id(domain.getId())
                .registrationNo(domain.getRegistrationNo())
                .firstName(domain.getFirstName())
                .lastName(domain.getLastName())
                .birthDate(domain.getBirthDate())
                .gender(domain.getGender()==null?null:EmployeeCnapsEntity.Gender.valueOf(domain.getGender().toString()))
                .phones(List.of())
                .address(domain.getAddress())
                .personalEmail(domain.getPersonalEmail())
                .professionalEmail(domain.getProfessionalEmail())
                .cinNumber(domain.getCinNumber())
                .cinIssueDate(domain.getCinIssueDate())
                .cinIssuePlace(domain.getCinIssuePlace())
                .function(domain.getFunction())
                .numberOfChildren(domain.getNumberOfChildren())
                .hiringDate(domain.getHiringDate())
                .departureDate(domain.getDepartureDate())
                .socioProfessionalCategory(domain.getSocioProfessionalCategory()==null?null:EmployeeCnapsEntity.SocioProfessionalCategory.valueOf(domain.getSocioProfessionalCategory().toString()))
                .cnapsNumber(domain.getCnapsNumber())
                .photo(domain.getPhoto())
                .build();
    }

}

