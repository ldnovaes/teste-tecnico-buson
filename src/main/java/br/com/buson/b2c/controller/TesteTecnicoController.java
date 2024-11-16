package br.com.buson.b2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.buson.b2c.model.InformacoesBolaCaixa;
import br.com.buson.b2c.service.TesteTecnicoService;

@RestController
@RequestMapping("/api/verifica")
public class TesteTecnicoController implements ITesteTecnicoController {
	
	@Autowired
	private TesteTecnicoService testeTecnicoService;

	@Override
	@PostMapping
	public ResponseEntity<String> verificaBolaBoliche(@RequestBody InformacoesBolaCaixa infoBolaCaixa) {
		// Não é preciso um if (bolaBolicheCabeEmCaixa). O metodo na camada de serviço ja devolve um erro caso a bola nao caiba. Esse erro é tratado em em br.com.buson.b2c.exception.handler.GlobalExceptionHandler
		// Apesar de não ser preciso, ter ele aqui é fundamental para que a validação ocorra. De todo modo também não tem problema algum fazê-lo aqui. É totalmente possível.
		boolean bolaBolicheCabeEmCaixa = this.testeTecnicoService.bolaBolicheCabeEmCaixa(infoBolaCaixa);
		return ResponseEntity.ok("S");
	}

}
