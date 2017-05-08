package br.com.lrsantos.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChamadoService {
	
	@Autowired
	private ChamadoRespository dao;
	
	public void inclui(ChamadoTecnico chamado) {
		this.dao.inclui(chamado);
	}
	
	public List<ChamadoTecnico> listaPorSituacao(String situacao) {
		return this.dao.listaPorSituacao(situacao);
	}
	
	public List<ChamadoTecnico> listaTodos(){
		return this.dao.listaTodos();
	}
}
