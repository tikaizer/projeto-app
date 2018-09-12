package com.projetoapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetoapp.model.Perfil;
import com.projetoapp.repository.PerfilRepository;
import com.projetoapp.service.PerfilService;

@CrossOrigin(origins  = "http://localhost")
@RestController
@RequestMapping("/perfil")
public class PerfilController {	
	
	
	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private PerfilService perfilService;	


	@PostMapping("perfil/novo")
	public ResponseEntity<Perfil> criar(@Valid @RequestBody Perfil perfil, HttpServletResponse response) {
		Perfil perfilSalva =perfilService.save(perfil);		
		return ResponseEntity.status(HttpStatus.CREATED).body(perfilSalva);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Perfil>> buscarTodos() {
		List<Perfil> perfis = perfilRepository.findAll();
		if (perfis != null)
			return ResponseEntity.ok(perfis);
		else
			return ResponseEntity.notFound().build();
	}
/*
	@GetMapping("/{codigo}")
	public ResponseEntity<Perfil> buscarPeloCodigo(@PathVariable Long codigo) {
		Perfil Perfil = perilRepository.findOne(codigo);
		return Perfil != null ? ResponseEntity.ok(Perfil) : ResponseEntity.notFound().build();
	}
	
	*/
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		perfilRepository.deleteById(codigo);
	}
	/*
	@PutMapping("/{codigo}")
	public ResponseEntity<Perfil> atualizar(@PathVariable Long codigo, @Valid @RequestBody Perfil Perfil) {
		Perfil UsuarioSalva = perfilService.atualizar(codigo, Perfil);
		return ResponseEntity.ok(UsuarioSalva);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		perfilService.atualizarPropriedadeAtivo(codigo, ativo);
	}
*/
	
}

	

