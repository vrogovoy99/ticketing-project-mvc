package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;

public interface TaskService extends CrudService<TaskDTO, Long> {
    Object findAllTasksByStatus(Status status);

    void updateTaskStatus(TaskDTO task);

    Object findAllTasksByStatusIsNot(Status status);
}
