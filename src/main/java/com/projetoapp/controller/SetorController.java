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
import com.projetoapp.model.Setor;
import com.projetoapp.repository.SetorRepository;
import com.projetoapp.service.SetorService;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("/setor")
public class SetorController {
	
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private SetorService setorService;	
	
	@PostMapping("/")
	public ResponseEntity<Setor> criar(@Valid @RequestBody Setor setor, HttpServletResponse response) {
		Setor setorSalva =setorService.save(setor);		
		return ResponseEntity.status(HttpStatus.CREATED).body(setorSalva);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Setor>> buscarTodos() {
		List<Setor> setors = setorRepository.findAll();
		if (setors != null)
			return ResponseEntity.ok(setors);
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping("/{codigo}")
	
	public ResponseEntity<Optional<Setor>> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Setor> setor = setorRepository.findById(codigo);
		
		if(setor != null)
			return ResponseEntity.ok(setor);
		else
			return ResponseEntity.notFound().build();
	}

	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		setorRepository.deleteById(codigo);
	}
	@RequestMapping(value="/", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel editar(@Valid @RequestBody Setor setor){
				
		
		try {
			
			this.setorService.save(setor);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}

}
