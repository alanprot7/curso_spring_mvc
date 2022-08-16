package br.com.homeoffice.registroescolar.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.homeoffice.registroescolar.models.Professor;
import br.com.homeoffice.registroescolar.models.StatusProfessor;

public class RequisicaoFormProfessor {

	@NotBlank
	@NotNull
	private String nome;
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal salario;
	private StatusProfessor statusProfessor;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public StatusProfessor getStatusProfessor() {
		return statusProfessor;
	}

	public void setStatusProfessor(StatusProfessor statusProfessor) {
		this.statusProfessor = statusProfessor;
	}
	
	public Professor toProfessor() {
		
		Professor professor = new Professor();
		professor.setNome(this.nome);
		professor.setSalario(this.salario);
		professor.setStatusProfessor(this.statusProfessor);
		
		return professor;
	}
	
	public Professor updProfessor(Professor professor) {
		professor.setNome(this.nome);
		professor.setSalario(this.salario);
		professor.setStatusProfessor(this.statusProfessor);
		return professor;
	}
	
	public void fromProfessor(Professor professor) {
		this.nome = professor.getNome();
		this.salario = professor.getSalario();
		this.statusProfessor = professor.getStatusProfessor();
	}
	

	@Override
	public String toString() {
		return "RequisicaoNovoProfessor [nome=" + nome + ", salario=" + salario + ", statusProfessor=" + statusProfessor
				+ "]";
	}
	
	

}
