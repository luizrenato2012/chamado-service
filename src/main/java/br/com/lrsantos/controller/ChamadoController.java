package br.com.lrsantos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.lrsantos.model.ChamadoService;
import br.com.lrsantos.model.ChamadoTecnico;
import br.com.lrsantos.model.Produto;

@Controller
@RequestMapping("/chamado")
 // habilita transacao em todos metodos do metodos da classe
public class ChamadoController {
	
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Autowired
	private ChamadoService chamadoService;
	
	private ChamadoNotificador notificador;
	
	@RequestMapping(value="/teste", method = RequestMethod.GET)
	@ResponseBody
	public String doTeste() {
		log.info("Testando");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				if (notificador==null) {
					notificador = new ChamadoNotificador();
				}
				notificador.notifica(ChamadoController.this.chamadoService.listaTodosJson());
			}
		}).start();
		return "teste ok";
	}

	@RequestMapping(value="/todos", method=RequestMethod.GET)
	@ResponseBody
	public List<ChamadoTecnico> listaTodos() {
		ResponseEntity<List<ChamadoTecnico>> entity ;
		List<ChamadoTecnico> chamados = null;
		try {
			chamados = this.chamadoService.listaTodos();
//			entity = new ResponseEntity<List<ChamadoTecnico>> (chamados, HttpStatus.OK);
		} catch (Exception e ) {
			e.printStackTrace();
//			HttpHeaders headers = new HttpHeaders();
//			headers.add("erro", e.getMessage());
//			entity = new ResponseEntity<List<ChamadoTecnico>> (chamados, headers,HttpStatus.BAD_REQUEST);	
		}
		return chamados;
	}
	

	private ChamadoTecnico criaChamado(Integer id, String cpf, String equipamento, String marca,
			String modelo, String defeito) {
		ChamadoTecnico chamado = new ChamadoTecnico();
		chamado.setId(id);
		chamado.setEquipamento(equipamento);
		chamado.setMarca(marca);
		chamado.setModelo(modelo);
		chamado.setDefeito(defeito);
		return chamado;
	}
	
	@RequestMapping(value="/{id}",  method=RequestMethod.GET)
	@ResponseBody
	public ChamadoTecnico encontra(@PathVariable("id")Integer id) {
		ChamadoTecnico chamado = this.chamadoService.encontra(id);
		return chamado;
	}
	
	@RequestMapping(value="/produto", method=RequestMethod.GET)
	@ResponseBody
	public Produto encontra() {
		Produto produto = null;
		try {
			produto= new Produto();
		} catch (Exception e ) {
			e.printStackTrace();
		}
		return produto;
	}
	
	@RequestMapping(value="/situacao/{situacao}", method=RequestMethod.GET)
	public ResponseEntity<List<ChamadoTecnico>> listaPorSituacao(@PathVariable("situacao") String situacao) {
		String retorno="";
		ResponseEntity<List<ChamadoTecnico>> response = null;
		List<ChamadoTecnico> lista = null;
		try {
			lista = this.chamadoService.listaPorSituacao(situacao);
			response = new ResponseEntity<List<ChamadoTecnico>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			HttpHeaders headers = new HttpHeaders();
			headers.add("erro", e.getMessage());
			response = new ResponseEntity<List<ChamadoTecnico>>(lista, headers,HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@Transactional
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> inclui( ChamadoTecnico chamado) {
		ResponseEntity<String> response = null;
		try {
			this.validaChamado(chamado);
			chamado.setDataAbertura(new Date());
			this.chamadoService.inclui(chamado);
			response = new ResponseEntity<String>("Ok", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	private void validaChamado(ChamadoTecnico chamado) {
		System.out.println("Recebendo chamado: \n"+ chamado);
		if(chamado==null) {
			throw new RuntimeException("Chamado invalido");
		}
		
		if(chamado.getCpf()==null || chamado.getCpf().trim().equals("")) {
			throw new RuntimeException("CPF invalido");
		}
	}
	
	
}
