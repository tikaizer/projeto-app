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

import com.projetoapp.model.Cliente;
import com.projetoapp.model.ResponseModel;
import com.projetoapp.repository.ClienteRepository;
import com.projetoapp.service.ClienteService;

@CrossOrigin(origins  = "http://localhost")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;	

	
	@PostMapping("/")
	public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalva =clienteService.save(cliente);		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalva);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Cliente>> buscarTodos() {
		List<Cliente> clientes = clienteService.buscarTodos();
		if (clientes != null)
			return ResponseEntity.ok(clientes);
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping("/{codigo}")	
	public ResponseEntity<Optional<Cliente>> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Cliente> cliente = clienteRepository.findById(codigo);
		
		if(cliente != null)
			return ResponseEntity.ok(cliente);
		else
			return ResponseEntity.notFound().build();
	}
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		clienteRepository.deleteById(codigo);
	}
	
	@RequestMapping(value="/", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel editar(@Valid @RequestBody Cliente cliente){
				
		
		try {
			
			this.clienteService.save(cliente);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}

	
}
