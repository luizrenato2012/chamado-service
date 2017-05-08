package br.com.lrsantos.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="chamado_service.chamado_tecnico")
@SequenceGenerator(name="SEQ_ID_CHAMADO", sequenceName="chamado_service.seq_id_chamado")
public class ChamadoTecnico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ID_CHAMADO")
	private Integer id;
	
	@Column(length=20)
	private String cpf;
	
	@Column(name="data_abertura")
	private Date dataAbertura;
	
	@Column(length=20)
	private String equipamento;
	
	@Column(length=20)
	private String marca;
	
	@Column(length=20)
	private String modelo;
	
	@Column(name="numero_serie", length=20)
	private String numeroSerie;
	
	@Column(length=50)
	private String defeito;
	
	@Enumerated(EnumType.STRING)
	private SituacaoChamado situacao;
	
	@OneToMany(mappedBy="chamado")
	private List<AtendimentoChamado> listaAtendimento;	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public String getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getNumeroSerie() {
		return numeroSerie;
	}
	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}
	public String getDefeito() {
		return defeito;
	}
	public void setDefeito(String defeito) {
		this.defeito = defeito;
	}
	public SituacaoChamado getSituacao() {
		return situacao;
	}
	public void setSituacao(SituacaoChamado situacao) {
		this.situacao = situacao;
	}
	
	public List<AtendimentoChamado> getListaAtendimento() {
		return listaAtendimento;
	}
	public void setListaAtendimento(List<AtendimentoChamado> listaAtendimento) {
		this.listaAtendimento = listaAtendimento;
	}
	@Override
	public String toString() {
		return "ChamadoTecnico [id=" + id + ", cpf=" + cpf + ", dataAbertura="
				+ dataAbertura + ", equipamento=" + equipamento + ", marca="
				+ marca + ", modelo=" + modelo + "]";
	}
	
}
