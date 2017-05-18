package br.com.lrsantos.controller;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ClientEndpoint
public class ChamadoNotificador {
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private Session session;
	private WebSocketContainer container;
	
	public ChamadoNotificador() {
		super();
		log.info(">>> Criando " + this.getClass().getName());
		
	}
	
	private void inicializa() {
		if (container== null) {
			log.info("Inicializando ");
			container = ContainerProvider.getWebSocketContainer();
			try {
				container.connectToServer(this, new URI("ws://10.0.20.98:8080/chamado-service/notificacao"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void notifica() {
		inicializa();
		log.info(">>> Notificando " );
		try {
			this.session.getBasicRemote().sendText("NOTIFICA");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@OnClose
	public void onFecha(Session session, CloseReason reason){
		log.info("Fechando conexao notificador: "+ reason.getReasonPhrase());
	}
	
	@OnOpen
	public void onAbre(Session session) {
		log.info(">>> abrindo conexao notificador " );
		this.session = session;
	}
	
	@OnMessage
	public void onRecebe(Session session, String mensagem) {
		log.info("Recebendo "+ mensagem);
	}

	@Override
	public String toString() {
		return "ChamadoNotificador []";
	}

	
	
	
}
