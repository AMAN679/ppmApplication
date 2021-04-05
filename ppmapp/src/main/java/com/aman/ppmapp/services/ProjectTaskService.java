package com.aman.ppmapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aman.ppmapp.domain.Backlog;
import com.aman.ppmapp.domain.Project;
import com.aman.ppmapp.domain.ProjectTask;
import com.aman.ppmapp.exceptions.ProjectNotFoundException;
import com.aman.ppmapp.repositories.BacklogRepository;
import com.aman.ppmapp.repositories.ProjectRepository;
import com.aman.ppmapp.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private ProjectService projectService;
	
	
	public ProjectTask addProjectTask(String projectIdentifier,ProjectTask projectTask,String username)
	{
		Backlog backlog=projectService.findProjectByIdentifier(projectIdentifier, username).getBacklog();
		
		projectTask.setBacklog(backlog);
		
		Integer BacklogSequence=backlog.getPTSequence();
		
		BacklogSequence++;
		backlog.setPTSequence(BacklogSequence);
		
		
		projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
		projectTask.setProjectIdentifier(projectIdentifier);

		if(  projectTask.getPriority()==null || projectTask.getPriority()==0)
		{
			projectTask.setPriority(3);
		}
		
		
		 if(projectTask.getStatus()=="" || projectTask.getStatus()==null)
		 {
			 projectTask.setStatus("TO_DO"); 
		 }
		 
		 
		 return projectTaskRepository.save(projectTask);
		
		
	}


	public Iterable<ProjectTask> findBacklogById(String id,String username) {

		projectService.findProjectByIdentifier(id, username);
		
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
 	}
	
	public ProjectTask findPTByProjectSequence(String backlog_id,String pt_id,String username)
	{
		
		projectService.findProjectByIdentifier(backlog_id, username);
		

		
		ProjectTask projectTask=projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask==null)
		{
			throw new ProjectNotFoundException("Project Task "+pt_id+" not found");
		}
		
		
		if(!projectTask.getProjectIdentifier().equals(backlog_id))
		{
			throw new ProjectNotFoundException("Project Task "+pt_id+" does not exist in project "+backlog_id);
		}
		
		return projectTask;
	}
	
	
	public ProjectTask updateByProjectSequence(ProjectTask updatedTask,String backlog_id,String pt_id,String username)
	{
		ProjectTask projectTask=findPTByProjectSequence(backlog_id, pt_id,username);
		projectTask=updatedTask;
		
		return projectTaskRepository.save(projectTask);
	}
	
	
	public void deletePTByProjectSequence(String backlog_id,String pt_id,String username)
	{
		ProjectTask projectTask=findPTByProjectSequence(backlog_id, pt_id,username);
		projectTaskRepository.delete(projectTask);	
	}	
}

