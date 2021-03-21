package com.aman.ppmapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.ppmapp.domain.Project;
import com.aman.ppmapp.exceptions.ProjectIdException;
import com.aman.ppmapp.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project)
	{
		//logic
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);	
		}
		catch(Exception e)
		{
			throw new ProjectIdException("Project ID "+project.getProjectIdentifier().toUpperCase()+" Already Exist");
		}
		
		
	}
	
	
	public Project findProjectByIdentifier(String projectId)
	{
		Project project=projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if(project==null)
			throw new ProjectIdException(projectId.toUpperCase() +" Does not Exist");

		return projectRepository.findByProjectIdentifier(projectId.toUpperCase());
	}

	public Iterable<Project> findAllProjects()
	{
		return projectRepository.findAll();
	}
	
	
	
	public void deleteProjectByIdentifier(String projectId)
	{
		Project project=projectRepository.findByProjectIdentifier(projectId);
		if(project==null)
			throw new ProjectIdException("Cannot find Project with ID: "+projectId);
		projectRepository.delete(project);
	}
	
	
	
	
	
	
	
	
	
	
	
}
