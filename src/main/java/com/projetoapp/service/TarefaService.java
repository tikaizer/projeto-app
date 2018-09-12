package com.projetoapp.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoapp.model.Tarefa;
import com.projetoapp.repository.TarefaRepository;


@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

		
	public List<Tarefa> buscarTodos(){		
		List<Tarefa> todos = tarefaRepository.findAll();
		return todos;
	}

	public Tarefa save(@Valid Tarefa tarefa) {
		return tarefaRepository.save(tarefa);
		 
	}

	public Tarefa buscarPorId(Long codigo){
		Tarefa tarefa = new Tarefa();
		tarefa = tarefaRepository.getOne(codigo);
		return tarefa;
	}
	
}
