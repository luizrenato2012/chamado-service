package br.com.lrsantos.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/todos")
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
	
	public ResponseEntity<String> inclui(ChamadoTecnico chamado) {
		String retorno="";
		ResponseEntity<String> response = null;
		try {
			this.chamadoService.inclui(chamado);
			response = new ResponseEntity<String>("Ok", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	public ResponseEntity<List<ChamadoTecnico>> listaPorSituacao(String situacao) {
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
}
