package com.projetoapp.model;

import java.io.Serializable;
import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Workflow implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	private String descricaoWork;
	private String titulo;
	@OneToOne
	private Tarefa descricaoTarefa;
	@OneToOne
	private Setor setor;
	@OneToOne
	private Cliente cliente;
	@OneToOne
	private Usuario usuario;	
	private LocalDate dataCadastroWorkflow;
	private LocalDate prazoWorkflow;
	private boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Tarefa getDescricaoTarefa() {
		return descricaoTarefa;
	}
	public void setDescricaoTarefa(Tarefa descricao) {
		this.descricaoTarefa = descricao;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public LocalDate getDataCadastroWorkflow() {
		return dataCadastroWorkflow;
	}
	public void setDataCadastroWorkflow(LocalDate dataCadastroWorkflow) {
		this.dataCadastroWorkflow = dataCadastroWorkflow;
	}
	public LocalDate getPrazoWorkflow() {
		return prazoWorkflow;
	}
	public void setPrazoWorkflow(LocalDate prazoWorkflow) {
		this.prazoWorkflow = prazoWorkflow;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getDescricaoWork() {
		return descricaoWork;
	}
	public void setDescricaoWork(String descriçãoWork) {
		this.descricaoWork = descriçãoWork;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Workflow other = (Workflow) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}