package br.com.buson.b2c.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformacoesBolaCaixa {
	
	private Integer diametroBolaBoliche;
	
	private List<Integer> medidasCaixa;
	
	private String medidasCaixaString;

}
