package br.com.lrsantos.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ChamadoRespository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void inclui(ChamadoTecnico chamado){
		this.entityManager.persist(chamado);
	}
	
	public void atualiza(ChamadoTecnico chamado){
		this.entityManager.merge(chamado);
	}
	
	public List<ChamadoTecnico> listaPorSituacao(SituacaoChamado situacao) {
		Query query = (Query) entityManager.createQuery("select c from ChamadoTecnico c where c.situacao= ?1");
		query.setParameter(1, situacao);
		return query.getResultList();
	}
	
	public List<ChamadoTecnico> listaTodos() {
		Query query = (Query) entityManager.createQuery("select c from ChamadoTecnico c");
		return query.getResultList();
	}
	
	public ChamadoTecnico encontra(Integer id){
		return this.entityManager.find(ChamadoTecnico.class, id);
	}
	
}
