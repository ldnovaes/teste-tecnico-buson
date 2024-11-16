package br.com.buson.b2c.exception;

public class MedidaNegativaOuMaiorQueDezMil extends RuntimeException {

	private static final long serialVersionUID = 2767147434655044447L;
	
	public MedidaNegativaOuMaiorQueDezMil(String messagemErro) {
		super(messagemErro);
	}

}
