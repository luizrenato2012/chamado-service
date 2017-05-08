package br.com.lrsantos.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.lrsantos.model.ChamadoService;
import br.com.lrsantos.model.ChamadoTecnico;

@Controller
@RequestMapping("/chamado")
@Transactional
public class ChamadoController {
	
	@Autowired
	private ChamadoService chamadoService;
	
	@RequestMapping("/teste")
	@ResponseBody
	public String doTeste() {
		return "teste ok";
	}
	
	public List<ChamadoTecnico> listaTodos() {
		return this.chamadoService.listaTodos();
	}
}
