package com.hei.project2p1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "registration_no_tracker")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationNoTracker {
    @Id
    private int id;
    @Column(name = "last_no")
    private Long lastNo;
}
