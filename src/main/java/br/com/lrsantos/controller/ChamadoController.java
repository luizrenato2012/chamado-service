package br.com.lrsantos.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.lrsantos.model.ChamadoService;
import br.com.lrsantos.model.ChamadoTecnico;
import br.com.lrsantos.model.SituacaoChamado;

@Controller
@RequestMapping("/chamado")
@Transactional // habilita transacao em todos metodos do metodos da classe
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;

	@RequestMapping("/teste")
	@ResponseBody
	public String doTeste() {
		return "teste ok";
	}

	@RequestMapping(value="/todos", method=RequestMethod.GET)
	public ResponseEntity<List<ChamadoTecnico>> listaTodos() {
		ResponseEntity<List<ChamadoTecnico>> entity ;
		List<ChamadoTecnico> lista = null;
		try {
			lista = this.chamadoService.listaTodos();
			entity = new ResponseEntity<List<ChamadoTecnico>> (lista, HttpStatus.OK);
		} catch (Exception e ) {
			e.printStackTrace();
			HttpHeaders headers = new HttpHeaders();
			headers.add("erro", e.getMessage());
			entity = new ResponseEntity<List<ChamadoTecnico>> (lista, headers,HttpStatus.BAD_REQUEST);	
		}
		return entity;
	}
	
	@RequestMapping(value="/situacao", method=RequestMethod.GET)
	public ResponseEntity<List<ChamadoTecnico>> listaPorSituacao(@PathVariable String situacao) {
		String retorno="";
		ResponseEntity<List<ChamadoTecnico>> response = null;
		List<ChamadoTecnico> lista = null;
		try {
			this.chamadoService.listaPorSituacao(situacao);
			response = new ResponseEntity<List<ChamadoTecnico>>(lista, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			HttpHeaders headers = new HttpHeaders();
			headers.add("erro", e.getMessage());
			response = new ResponseEntity<List<ChamadoTecnico>>(lista, headers,HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<String> inclui(@RequestBody ChamadoTecnico chamado) {
		ResponseEntity<String> response = null;
		try {
			this.validaChamado(chamado);
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
