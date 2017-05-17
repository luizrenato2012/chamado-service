package br.com.lrsantos.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ChamadoNotificador implements Runnable {

	
	public ChamadoNotificador() {
		super();
		System.out.println(">>> Criando " + this.getClass().getName());
	}
	
	public void notifica() {
		System.out.println(">>> Notificando " );
	}

	@Override
	public String toString() {
		return "ChamadoNotificador []";
	}

	@Override
	public void run() {
		System.out.println(">>> rodando" );
	}
	
}
