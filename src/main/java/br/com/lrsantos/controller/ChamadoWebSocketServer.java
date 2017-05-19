package br.com.lrsantos.controller;

import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/notificacao")
public class ChamadoWebSocketServer {
	
	Logger log = Logger.getLogger(this.getClass().getName());
	
	@OnMessage
	public void enviaMensagem (Session session, String mensagem) {
		log.info((String.format("Mensagem recebida [%s]", mensagem)));
		if (mensagem==null || mensagem.trim().equals("")) {
			log.info(">>> mensagem vazia");
		} else {
			this.enviaNotificacao(session, mensagem);
		}
	}
	
	private void enviaNotificacao(Session sessao, String mensagem) {
		for(Session sessaoCliente :sessao.getOpenSessions() ) {
			try {
				sessaoCliente.getBasicRemote().sendText(mensagem);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@OnOpen 
	public void iniciandoConexao(Session session, EndpointConfig config) {
		System.out.println(">> Conexao aberta");
	}
	
	public void onError(Session session, Throwable exception) {
		log.info(">>> erro");
		exception.printStackTrace();
	}
}
