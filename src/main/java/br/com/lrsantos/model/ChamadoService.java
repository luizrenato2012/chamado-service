package br.com.lrsantos.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChamadoService {
	
	private Map<String,SituacaoChamado> mapSituacao;
	
	public ChamadoService() {
		mapSituacao = new HashMap<String,SituacaoChamado>();
		mapSituacao.put("aberto", SituacaoChamado.ABERTO);
		mapSituacao.put("fechado", SituacaoChamado.FECHADO);
		mapSituacao.put("enviado", SituacaoChamado.ENVIADO);
		mapSituacao.put("atendimento", SituacaoChamado.EM_ATENDIMENTO);
	}

	@Autowired
	private ChamadoRespository dao;
	
	public void inclui(ChamadoTecnico chamado) {
		this.dao.inclui(chamado);
	}
	
	public List<ChamadoTecnico> listaPorSituacao(String situacao) {
		if(!mapSituacao.containsKey(situacao)){
			throw new RuntimeException("Situacao "+ situacao + " invalida");
		}
		return this.dao.listaPorSituacao(mapSituacao.get(situacao));
	}
	
	public List<ChamadoTecnico> listaTodos(){
		return this.dao.listaTodos();
	}

	public ChamadoTecnico encontra(Integer id) {
		return this.dao.encontra(id);
	}
	
	public String listaTodosJson() {
		ObjectMapper mapper = new ObjectMapper();
		List<ChamadoTecnico> lista = this.listaTodos();
		String jsonLista = "";
		try {
			jsonLista = mapper.writeValueAsString(lista);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonLista;
	}
}
