package com.api.employeemanager.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class EmployeeDTO {
    @Null(message = "{Dto.NO_ID}")
    private Long id;
    @NotNull(message = "{Dto.NAME_ABSENT}")
    @Size(min = 3, message = "{Dto.NAME_INCORRECT}")
    private String name;
    @NotNull(message = "{Dto.EMAIL_ABSENT}")
    @Email(message = "{Dto.EMAIL_INCORRECT}")
    private String email;
    @NotNull(message = "{Dto.JOB_ABSENT}")
    @Size(min = 2, message = "{Dto.JOB_INCORRECT}")
    private String jobTitle;
    @NotNull(message = "{Dto.PHONE_ABSENT}")
    @Size(min = 10, message = "{Dto.PHONE_INCORRECT}")
    private String phone;
    private String imageUrl;

    public EmployeeDTO(){}
    public EmployeeDTO(String name, String email, String jobTitle, String phone, String imageUrl) {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phone = phone;
        this.imageUrl = imageUrl;
    }
}
