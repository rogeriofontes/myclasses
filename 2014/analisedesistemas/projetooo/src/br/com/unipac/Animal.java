package br.com.unipac;

public class Animal {
	private Integer sexo;
	private String especie;
	private String peso;

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	public void comer() {
		System.out.println("Vai comer, porra!");
	}

}
