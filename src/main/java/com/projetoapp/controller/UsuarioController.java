package com.projetoapp.controller;

import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.RestController;

import com.projetoapp.model.ResponseModel;
import com.projetoapp.model.Usuario;
import com.projetoapp.repository.UsuarioRepository;
import com.projetoapp.service.UsuarioService;

@CrossOrigin(origins  = "http://localhost")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;	

	/*
	@PostMapping("/")
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalva =usuarioService.save(usuario);		
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalva);
	}
	*/
	@RequestMapping(value="/", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@Valid @RequestBody Usuario pessoa){
				
		
		try {
			
			this.usuarioService.save(pessoa);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}
	
	@RequestMapping(value="/", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel editar(@Valid @RequestBody Usuario pessoa){
				
		
		try {
			
			this.usuarioService.save(pessoa);
			
			return new ResponseModel(1,"Registro salvo com sucesso!");
			
		}catch(Exception e) {
			
			return new ResponseModel(0,e.getMessage());			
		}
	}
	
	
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> buscarTodos() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		
		if (usuarios != null)
			return ResponseEntity.ok(usuarios);
		else
			return ResponseEntity.notFound().build();
	}

	@GetMapping("/{codigo}")
	//@RequestMapping(value="/{codigo}", method = RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Optional<Usuario>> buscarPeloCodigo(@PathVariable Long codigo) {
		Optional<Usuario> usuario = usuarioRepository.findById(codigo);
		
		if(usuario != null)
			return ResponseEntity.ok(usuario);
		else
			return ResponseEntity.notFound().build();
	}
	/*
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		usuarioRepository.deleteById(codigo);
	}
	*/
	@RequestMapping(value="/{codigo}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Long codigo){
		
		Usuario usu = usuarioService.buscarPorId(codigo);
		
		try {
			
			usuarioService.excluir(usu);
			
			return new ResponseModel(1, "Registro excluido com sucesso!");
			
		}catch(Exception e) {
			return new ResponseModel(0, e.getMessage());
		}
	}
	/*
	@PutMapping("/{codigo}")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Usuario Usuario) {
		Usuario UsuarioSalva = usuarioService.atualizar(codigo, Usuario);
		return ResponseEntity.ok(UsuarioSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		usuarioService.atualizarPropriedadeAtivo(codigo, ativo);
	}
*/
	
}
