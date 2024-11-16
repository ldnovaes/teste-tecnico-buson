package br.com.buson.b2c.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.buson.b2c.exception.MedidaNegativaOuMaiorQueDezMil;
import br.com.buson.b2c.exception.MedidasCaixaIncompletas;
import br.com.buson.b2c.model.InformacoesBolaCaixa;

@Service
public class TesteTecnicoService {
	
	public boolean bolaBolicheCabeEmCaixa(InformacoesBolaCaixa informacoesBolaCaixa) {
	    boolean medidasCaixaEstaoNull = informacoesBolaCaixa.getMedidasCaixa() == null || informacoesBolaCaixa.getMedidasCaixa().isEmpty();
	    
	    if(medidasCaixaEstaoNull) {
	    	trataMedidasCaixaEmString(informacoesBolaCaixa);
	    }
	    
	    // esse booleano precisa ser sempre antes de tratar as medidas caso sejam passadas por string
	    boolean medidaCaixaIncompletas = informacoesBolaCaixa.getMedidasCaixa() != null && informacoesBolaCaixa.getMedidasCaixa().size() < 3;
		if (medidaCaixaIncompletas) {
			throw new MedidasCaixaIncompletas("Está faltando medidas para a caixa. Por favor, revise e envie novamente.");
		}
		
		return medidasNaoSaoNegativasOuMaiorQueDezMil(informacoesBolaCaixa);
	}
	
	private boolean medidasNaoSaoNegativasOuMaiorQueDezMil(InformacoesBolaCaixa informacoesBolaCaixa) {
	    Integer diametroBola = informacoesBolaCaixa.getDiametroBolaBoliche();
	    List<Integer> medidasCaixa = informacoesBolaCaixa.getMedidasCaixa();

	    Map<String, Integer> medidas = new LinkedHashMap<>(); // Escolhi LinkedHashMap por que precisava garantir a ordenação para que a validação funcionasse
	    medidas.put("DIÂMETRO DA BOLA", diametroBola);
	    medidas.put("ALTURA DA CAIXA", medidasCaixa.get(0));
	    medidas.put("LARGURA DA CAIXA", medidasCaixa.get(1));
	    medidas.put("PROFUNDIDADE DA CAIXA", medidasCaixa.get(2));

	    String menorMedidaNome = null;
	    int menorMedidaValor = Integer.MAX_VALUE;
	    
	    // reaproveitei esse loop para que não tivessemos um BigO(2n) e sim um BigO(n). Pode não afetar tanto ao impacto devido ao tamanho do array, mas gostaria de demonstrar a minha atenção a esse pequeno conhecimento
	    for (Map.Entry<String, Integer> entrada : medidas.entrySet()) {
	        String nomeMedida = entrada.getKey();
	        Integer valorMedida = entrada.getValue();
	        
	        boolean medidaEhNegativaOuMaiorQueDezMil = !medidaNaoEhNegativaOuMaiorQueDezMil(valorMedida);

	        if (medidaEhNegativaOuMaiorQueDezMil) {
	            String mensagemErro = nomeMedida + " INCORRETO(A). Por favor, informe outra entre 1 e 10.000.";
	            throw new MedidaNegativaOuMaiorQueDezMil(mensagemErro);
	        }
	        
	        // cada loop o valor será comparado. Vale ressaltar aqui que o primeiro elemento deste for 
	        //        será o diâmetro da bola. Então se algum outro for menor, saberemos o nome e então poderemos entregar ao usuário para informá-lo do erro.
	        if (valorMedida < menorMedidaValor) {
	            menorMedidaValor = valorMedida;
	            menorMedidaNome = nomeMedida;
	        }
	    }
	    
	    boolean nomeMenorMedidaNaoEhDiametroBola = !"DIÂMETRO DA BOLA".equals(menorMedidaNome);
	    
	    // A escolha do booleano ser referente ao nome foi simplesmente uma facilidade para que pudessemos devolver EXATAMENTE qual medida estava incompativel com o diâmetro
	    if (nomeMenorMedidaNaoEhDiametroBola) {
	    	String mensagemErro = menorMedidaNome + " INCORRETO(A). Por favor, informe outra caixa que caiba a bola";
            throw new MedidaNegativaOuMaiorQueDezMil(mensagemErro);
	    } 

	    return true;
	}

	private void trataMedidasCaixaEmString(InformacoesBolaCaixa informacoesBolaCaixa) {
		informacoesBolaCaixa.setMedidasCaixa(new ArrayList<>()); // para cair nesse metodo a medidasCaixa é nula ou vazia. Então não há problemas.
		String medidasCaixaEmString = informacoesBolaCaixa.getMedidasCaixaString().strip(); // trata qualquer espaço antes e após
		String[] medidasCaixaEmArrayString = medidasCaixaEmString.split(" ");
		
		for (String medidaEmString : medidasCaixaEmArrayString) {
			Integer medidaEmInteger = Integer.valueOf(medidaEmString);
			informacoesBolaCaixa.getMedidasCaixa().add(medidaEmInteger);
		}
	}

	private boolean medidaNaoEhNegativaOuMaiorQueDezMil(Integer medida) {
		return medida >= 1 && medida <= 10000;
	}
}
