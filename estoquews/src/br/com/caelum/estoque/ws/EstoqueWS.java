package br.com.caelum.estoque.ws;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.item.ListaItens;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

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
	public Item cadastrarItem(
			@WebParam(name = "tokenUsuario", header=true) TokenUsuario usuario,
			@WebParam(name = "item") Item item) 
					throws AutorizacaoException, ParseException {
		
		Date data = new SimpleDateFormat("dd/MM/yyyy").parse("2015-12-ss31T00:00:00");
		System.out.println(data);
		
		System.out.println("Cadastrando itens");
		
		boolean ehValido = new TokenDao().ehValido(usuario);
		
		if (!ehValido) {
			throw new AutorizacaoException("Falha na autenticação!");
		}
		
		new ItemValidador(item).validate();
		this.dao.cadastrar(item);
		System.out.println("Item cadastrado: " + item);
		return item;
	}

}
