package com.projetoapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetoapp.model.ResponseModel;
import com.projetoapp.model.Tarefa;
import com.projetoapp.repository.TarefaRepository;
import com.projetoapp.service.TarefaService;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/tarefas")
public class TarefaController {

	
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TarefaService tarefaService;	

	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@Valid @RequestBody Tarefa tarefa){				
		
		try {
			
			this.tarefaService.save(tarefa);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}
	
	@CrossOrigin
	@GetMapping("/")
	public ResponseEntity<List<Tarefa>> buscarTodos() {
		List<Tarefa> tarefas = tarefaService.buscarTodos();
		if (tarefas != null)
			return ResponseEntity.ok(tarefas);
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping("/{codigo}")
	//@RequestMapping(value="/{codigo}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Optional<Tarefa>> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(codigo);
		
		if(tarefa != null)
			return ResponseEntity.ok(tarefa);
		else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		tarefaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Tarefa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Tarefa tarefa) {
		Tarefa tarefaSalva = tarefaService.buscarPorId(codigo);
		tarefaSalva = tarefa;
		tarefaSalva = tarefaService.save(tarefaSalva);
		return ResponseEntity.ok(tarefaSalva);
	}
	@RequestMapping(value="/", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel editar(@Valid @RequestBody Tarefa tarefa){
				
		
		try {
			
			this.tarefaService.save(tarefa);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}
	
	
}
