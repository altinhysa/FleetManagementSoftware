package com.spines.fleetmanagementsoftware.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    private Date birthdate;

    private String address;

    private String email;

    private String telephone;

    private boolean active;

    private boolean available;

    private Date createdDate;

    private Date lastModifiedDate;
}
