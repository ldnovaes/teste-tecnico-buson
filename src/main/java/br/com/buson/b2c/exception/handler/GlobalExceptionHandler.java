package br.com.buson.b2c.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.buson.b2c.exception.MedidaNegativaOuMaiorQueDezMil;
import br.com.buson.b2c.exception.MedidasCaixaIncompletas;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MedidaNegativaOuMaiorQueDezMil.class)
    public ResponseEntity<String> handleBolaNaoCabeException(MedidaNegativaOuMaiorQueDezMil ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
    
    @ExceptionHandler(MedidasCaixaIncompletas.class)
    public ResponseEntity<String> handleBolaNaoCabeException(MedidasCaixaIncompletas ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }
    
}