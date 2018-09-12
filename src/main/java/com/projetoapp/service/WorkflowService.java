package com.projetoapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projetoapp.model.Workflow;
import com.projetoapp.repository.WorkflowRepository;

@Service
public class WorkflowService {

	
	@Autowired
	private WorkflowRepository workRepository;
		
	public List<Workflow> buscarTodos(){		
		List<Workflow> todos = workRepository.findAll();
		return todos;
	}

	public Workflow save(@Valid Workflow workflow) {
		return workRepository.save(workflow);
		 
	}
}
