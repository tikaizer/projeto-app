package com.projetoapp.service;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.projetoapp.model.Usuario;
import com.projetoapp.repository.UsuarioRepository;

@Service
public class UsuarioService {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	public List<Usuario> buscarTodos(){		
		List<Usuario> todos = usuarioRepository.findAll();
		return todos;
	}

	public Usuario save(@Valid Usuario usuario) {
		return usuarioRepository.save(usuario);		 
	}	
	public Usuario buscarPorId(Long id) {
		Usuario usu = new Usuario();
		usu = usuarioRepository.getOne(id);
		return usu;
	}
	
	public void excluir(Usuario usuario) {
		
		usuarioRepository.delete(usuario);
		
	}
}
