package com.projetoapp.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetoapp.model.ResponseModel;
import com.projetoapp.model.Workflow;
import com.projetoapp.repository.WorkflowRepository;
import com.projetoapp.service.WorkflowService;

@CrossOrigin(origins  = "http://localhost")
@RestController
@RequestMapping("/workflow")
public class WorkflowController {
	
	
	@Autowired
	private WorkflowRepository workflowRepository;
	
	@Autowired
	private WorkflowService workflowService;	
	
	@PostMapping("/")
	public ResponseEntity<Workflow> criar(@Valid @RequestBody Workflow workflow, HttpServletResponse response) {
		Workflow workflowSalva =workflowService.save(workflow);		
		return ResponseEntity.status(HttpStatus.CREATED).body(workflowSalva);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Workflow>> buscarTodos() {
		List<Workflow> workflows = workflowRepository.findAll();
		if (workflows != null)
			return ResponseEntity.ok(workflows);
		else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		workflowRepository.deleteById(codigo);
	}
	
	@GetMapping("/{codigo}")	
	public ResponseEntity<Optional<Workflow>> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Workflow> usuario = workflowRepository.findById(codigo);
		
		if(usuario != null)
			return ResponseEntity.ok(usuario);
		else
			return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value="/", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel editar(@Valid @RequestBody Workflow workflow){
				
		
		try {
			
			this.workflowService.save(workflow);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}


	
}
