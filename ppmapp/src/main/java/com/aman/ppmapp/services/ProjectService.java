package com.aman.ppmapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.ppmapp.domain.Project;
import com.aman.ppmapp.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project)
	{
		//logic
		return projectRepository.save(project);
	}

	
	
}
