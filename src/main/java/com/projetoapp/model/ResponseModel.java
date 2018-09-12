package com.projetoapp.model;


	public class ResponseModel {

		private int id;
		private String mensagem;
		
		public ResponseModel() {}
			
		public ResponseModel(int id, String mensagem) {
			this.id   = id;
			this.mensagem =  mensagem;
		}
		
		public int getCodigo() {
			return id;
		}
		public void setCodigo(int id) {
			this.id = id;
		}
		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

	}
