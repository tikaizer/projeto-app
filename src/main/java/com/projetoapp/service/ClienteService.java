package com.projetoapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoapp.model.Cliente;
import com.projetoapp.repository.ClienteRepository;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository cliRepository;

		
	public List<Cliente> buscarTodos(){		
		List<Cliente> todos = cliRepository.findAll();
		return todos;
	}

	public Cliente save(@Valid Cliente cliente) {		
		
			return cliRepository.save(cliente);
	}
	
	public Cliente buscarPorId(Long codigo){	
		return cliRepository.getOne(codigo);
	}

}
