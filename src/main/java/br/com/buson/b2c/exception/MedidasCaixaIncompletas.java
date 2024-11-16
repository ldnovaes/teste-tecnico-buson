package br.com.buson.b2c.exception;

public class MedidasCaixaIncompletas extends RuntimeException {

	private static final long serialVersionUID = 7557967649330370240L;

	public MedidasCaixaIncompletas(String messagemErro) {
		super(messagemErro);
	}

}
