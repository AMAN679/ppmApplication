package com.aman.ppmapp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aman.ppmapp.domain.ProjectTask;

public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long>{

	List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
	
	ProjectTask findByProjectSequence(String sequence);
	
}
