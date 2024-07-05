package com.i2i.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
public class ResponseStudentDto {
    private int id;
    private String name;
    private LocalDate dob;
    private int age;
    private ResponseAddressDto address;
    private ResponseGradeDto grade;
    private Set<CreateRoleDto> roles;
}
