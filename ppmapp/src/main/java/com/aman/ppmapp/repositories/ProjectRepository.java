package com.aman.ppmapp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.aman.ppmapp.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{

   Project findByProjectIdentifier(String projectId);
   
   Iterable<Project> findAll();
	
	}
	