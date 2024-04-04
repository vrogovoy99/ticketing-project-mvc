package com.cydeo.service;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.impl.AbstractMapService;

public interface ProjectService extends CrudService<ProjectDTO, String> {
    void complete(ProjectDTO project);

}
