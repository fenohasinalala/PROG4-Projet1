package com.hei.project2p1.repository.entity.validator;

import com.hei.project2p1.exception.BadRequestException;
import com.hei.project2p1.repository.entity.EmployeeEntity;
import com.hei.project2p1.repository.entity.PhoneEntity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class EmployeeEntityValidator implements Consumer<EmployeeEntity> {
  @Override public void accept(EmployeeEntity employee) {
    Set<String> violationMessages = new HashSet<>();
    if (employee.getFirstName() == null) {
      violationMessages.add("firstName not null");
    }
    if (employee.getLastName() == null) {
      violationMessages.add("lastname not blanc");
    }
    if (employee.getFirstName() == "") {
      violationMessages.add("firstName not blanc");
    }
    if (employee.getLastName() == "") {
      violationMessages.add("lastname not null");
    }
    if (employee.getPersonalEmail() != null) {
      if (!validateEmailRegex(employee.getPersonalEmail())) {
        violationMessages.add("Email should have email format");
      }
    }
    if (employee.getProfessionalEmail() != null) {
      if (!validateEmailRegex(employee.getProfessionalEmail())) {
        violationMessages.add("Email should have email format");
      }
    }
    if (employee.getCinNumber() != null) {
      if (employee.getCinNumber().length() != 12) {
        violationMessages.add("the CIN number length have to be 12, but yours is : " + employee.getCinNumber().length() );
      }
      if (!validateCinRegex(employee.getCinNumber())) {
        violationMessages.add("CIN should Be a alphanumeric");
      }
    }
    if (!violationMessages.isEmpty()) {
      String formattedViolationMessages = violationMessages.stream()
          .map(String::toString)
          .collect(Collectors.joining(". "));
      throw new BadRequestException(formattedViolationMessages);
    }
  }
  public static boolean validateEmailRegex(String email) {
    String regexPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}$";
    Pattern pattern = Pattern.compile(regexPattern);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static boolean validateCinRegex(String number) {
    String regexPattern = "^[a-zA-Z0-9]+$";
    Pattern pattern = Pattern.compile(regexPattern);
    Matcher matcher = pattern.matcher(number);
    return matcher.matches();
  }
}