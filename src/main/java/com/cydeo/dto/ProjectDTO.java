package com.cydeo.dto;

import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {
    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //this will convert text date input into LocalDate
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd") //this will convert text date input into LocalDate
    private LocalDate endDate;
    private String projectDetail;
    private Status projectStatus;
}
