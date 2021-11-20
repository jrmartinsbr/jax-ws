package br.com.caelum.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublicaEstoqueWS {
	
	public static void main(String[] args) {
		
		EstoqueWS estoqueWS = new EstoqueWS();
		String url = "http://localhost:8081/estoquews";
		
		System.out.println("EstoqueWS rodando: " + url);
		
		Endpoint.publish(url, estoqueWS);
		
	}

}
