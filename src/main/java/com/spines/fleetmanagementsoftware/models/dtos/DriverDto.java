package com.spines.fleetmanagementsoftware.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto {
    private Integer id;

    @NotBlank(message = "Invalid Name: Empty name")
    @NotNull(message = "Invalid Name: Name is NULL")
    @Size(min = 3, max = 30, message = "Invalid Name: Must be of 3 - 30 characters")
    private String name;

    @NotBlank(message = "Invalid Surname: Empty surname")
    @NotNull(message = "Invalid Surname: Surname is NULL")
    @Size(min = 3, max = 30, message = "Invalid Surname: Must be of 3 - 30 characters")
    private String surname;

    @NotNull(message = "Invalid Birthdate: Birthdate is NULL")
    private Date birthdate;

    @NotBlank(message = "Invalid address number: Empty address")
    @NotNull(message = "Invalid address number: Address is NULL")
    private String address;

    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Invalid Phone number: Empty number")
    @NotNull(message = "Invalid Phone number: Number is NULL")
    private String telephone;

    private boolean active;

    private boolean available;

}
