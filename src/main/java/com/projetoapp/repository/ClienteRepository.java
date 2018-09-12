package com.projetoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoapp.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
