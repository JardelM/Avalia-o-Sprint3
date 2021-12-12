package br.com.alura.forum.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.alura.forum.modelo.Estado;
import br.com.alura.forum.modelo.Regiao;


public class EstadoDTO {

	private Long id;
	private String nome;
	@Enumerated (EnumType.STRING)
	private Regiao regiao;
	private Integer populacao;
	private String capital;
	private Double area;

	public EstadoDTO(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
		this.regiao = estado.getRegiao();
		this.populacao = estado.getPopulacao();
		this.capital = estado.getCapital();
		this.area = estado.getArea();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public Integer getPopulacao() {
		return populacao;
	}

	public String getCapital() {
		return capital;
	}

	public Double getArea() {
		return area;
	}

	public static List<EstadoDTO> converter(List<Estado> estado) {
		return estado.stream().map(EstadoDTO::new).collect(Collectors.toList());

	}

	@Override
	public String toString() {
		return ("id = " + this.id + " nome = " + this.nome + " regiao :" + this.regiao + " populacao = "
				+ this.populacao + " capital = " + this.capital + " area = " + this.area);
	}

}
