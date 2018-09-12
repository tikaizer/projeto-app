package com.projetoapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoapp.model.Perfil;
import com.projetoapp.repository.PerfilRepository;

@Service
public class PerfilService {

	@Autowired
	private PerfilRepository perfilRepository;
	
	public List<Perfil> buscarTodos(){		
		List<Perfil> todos = perfilRepository.findAll();
		return todos;
	}

	public Perfil save(@Valid Perfil perfil) {
		return perfilRepository.save(perfil);
		 
	}
}
