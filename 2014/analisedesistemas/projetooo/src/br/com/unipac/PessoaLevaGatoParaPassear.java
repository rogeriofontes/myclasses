package br.com.unipac;

public class PessoaLevaGatoParaPassear {

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa();
		
		pessoa.setNome("Núbia");
		
		Gato gato = new Gato();
		gato.setNome("thor");
		gato.setDono(pessoa);
		
		System.out.println("Dono do " + gato.getNome() + " é " + gato.getDono().getNome() );
	}

}
