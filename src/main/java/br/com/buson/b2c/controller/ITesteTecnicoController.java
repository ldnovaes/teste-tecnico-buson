package br.com.buson.b2c.controller;

import org.springframework.http.ResponseEntity;

import br.com.buson.b2c.model.InformacoesBolaCaixa;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface ITesteTecnicoController {
	
	@Operation(
	    summary = "Verifica se a bola cabe ou não dentro da caixa",
	    description = "Retorna se a bola cabe ou não"
	)
    @ApiResponse(responseCode = "200", description = "A bola cabe dentro da caixa")
	@ApiResponse(responseCode = "400", description = "A bola não dentro da caixa. Informa qual medida está incorreta")
	ResponseEntity<	String> verificaBolaBoliche(InformacoesBolaCaixa infoBolaCaixa);

}
