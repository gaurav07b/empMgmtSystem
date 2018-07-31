package com.ems.service;

import java.util.List;

import com.ems.dto.ProjectDto;

public interface IProjectService {

	List<ProjectDto> getAllProject();
	String deleteProject(int projId);
	ProjectDto findOneProject(int projId);
	ProjectDto addNewProject(ProjectDto newDto);
	
}
