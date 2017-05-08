package br.com.lrsantos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="chamado_service.atendimento")
@SequenceGenerator(name="SEQ_ID_ATENDIMENTO", sequenceName="chamado_service.seq_id_atendimento")
public class AtendimentoChamado {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_ATENDIMENTO")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_hora")
	private Date dataHora;
	
	@Column(length=50)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="id_chamado")
	private ChamadoTecnico chamado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDataHora() {
		return dataHora;
	}
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ChamadoTecnico getChamado() {
		return chamado;
	}
	public void setChamado(ChamadoTecnico chamado) {
		this.chamado = chamado;
	}

}
