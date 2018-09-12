package com.projetoapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoapp.model.Workflow;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
	
	
	public Optional<Workflow> findById (Long id);

}
