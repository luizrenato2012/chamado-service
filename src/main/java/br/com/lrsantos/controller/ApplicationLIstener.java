package br.com.lrsantos.controller;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLIstener implements ApplicationListener<ApplicationEvent> {

	private ChamadoNotificador notificador;

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
//	@Override
//	public void contextDestroyed(ServletContextEvent evt) {
//		System.out.println(">>> Finalizado contexto");
//	}
//
//	@Override
//	public void contextInitialized(ServletContextEvent evt) {
//		System.out.println(">>> Iniciado contexto");
//		notificador = new ChamadoNotificador();
//		ServletContext context =  evt.getServletContext();
//		context.setAttribute("notificador", notificador);
//	}

}
