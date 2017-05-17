package br.com.lrsantos.controller;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("hello")
public class ChamadoWebSocketServer {
	
	@OnMessage
	public void enviaMensagem (Session session, String mensagem) {
		
	}
	
	@OnOpen
	public void recebeMensagem() {
		System.out.println("Conexao aberta");
	}
}
