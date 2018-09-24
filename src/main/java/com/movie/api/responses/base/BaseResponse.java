package com.movie.api.responses.base;

import java.io.Serializable;

import com.movie.api.responses.MessageResponse;

public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private MessageResponse mensagem;

	public MessageResponse getMensagem() {
		return mensagem == null ? new MessageResponse(200, "OK") : mensagem;
	}

	public void setMensagem(MessageResponse mensagem) {
		this.mensagem = mensagem;
	}
}