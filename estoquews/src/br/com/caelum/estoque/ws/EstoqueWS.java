package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.ResponseWrapper;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;

@WebService
public class EstoqueWS {
	
	ItemDao dao = new ItemDao();
	
	@WebMethod(operationName = "ConsultarItens")
	@WebResult(name = "itens")
	@ResponseWrapper(localName = "retornoConsultarItens")
	public ListaItens getItens(@WebParam(name = "filtros") Filtros filtros) {
		List<Filtro> lista = filtros.getLista();
		System.out.println("Chamando todosItens()");
		ListaItens listaItens = new ListaItens(dao.todosItens(lista));
		return listaItens;
	}
	
	@WebMethod(operationName = "CadastrarItem")
	@WebResult(name = "item")
	public Item cadastrarItem(@WebParam(name = "item") Item item) {
		this.dao.cadastrar(item);
		return item;
	}

}
