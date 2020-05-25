package br.com.caelum.estoque.ws;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

@WebService
public class EstoqueWS {
	
	ItemDao dao = new ItemDao();
	
	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "itens")
	public ListaItens getItens() {
		System.out.println("Chamando todosItens()");
		return new ListaItens(dao.todosItens());
	}

}
