package com.cydeo.dto;


import com.cydeo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
//@AllArgsConstructor - need to create constructor to exclude initiation of id
@Data
public class TaskDTO {
    private Long id; // will be assigned in database
    private ProjectDTO project;
    private UserDTO assignedEmployee;
    private String taskSubject;
    private String taskDetail;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate assignedDate;
    private Status status;

    public TaskDTO(ProjectDTO project, UserDTO assignedEmployee, String taskSubject, String taskDetail, LocalDate assignedDate, Status status) {

        this.id= UUID.randomUUID().getMostSignificantBits();

        this.project = project;
        this.assignedEmployee = assignedEmployee;
        this.taskSubject = taskSubject;
        this.taskDetail = taskDetail;
        this.assignedDate = assignedDate;
        this.status = status;
    }
}
