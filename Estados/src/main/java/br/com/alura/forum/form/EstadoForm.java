package br.com.alura.forum.form;



import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;

import br.com.alura.forum.modelo.Estado;
import br.com.alura.forum.modelo.Regiao;
import br.com.alura.forum.repository.EstadoRepository;

public class EstadoForm {

	private Long id;
	private String nome;
	@Enumerated (EnumType.STRING)
	private Regiao regiao;
	private Integer populacao;
	private String capital;
	private Double area;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public @NotEmpty Regiao getRegiao() {
		return regiao;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Estado converter(EstadoRepository estadoRepository) {
		return new Estado(nome, regiao, populacao, capital, area);
	}

	public Estado atualiza(Long id, EstadoRepository estadoRepository) {
		Estado estado = estadoRepository.getOne(id);
		
		estado.setNome(this.nome);
		estado.setRegiao(this.regiao);
		estado.setPopulacao(this.populacao);
		estado.setCapital(this.capital);
		estado.setArea(this.area);
		
		return estado;
	}

}
