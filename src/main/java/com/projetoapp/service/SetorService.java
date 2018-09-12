package com.projetoapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoapp.model.Setor;
import com.projetoapp.repository.SetorRepository;


@Service
public class SetorService {

	@Autowired
	private SetorRepository setorRepository;

		
	public List<Setor> buscarTodos(){		
		List<Setor> todos = setorRepository.findAll();
		return todos;
	}

	public Setor save(@Valid Setor setor) {
		return setorRepository.save(setor);
		 
	}
}
