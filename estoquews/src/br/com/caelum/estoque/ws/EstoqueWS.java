package br.com.caelum.estoque.ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {

	private ItemDao itemDao = new ItemDao();
	private TokenDao tokenDao = new TokenDao();

	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "itens")
	public ListaItens getItens(@WebParam(name = "filtros") Filtros filtros) {
		System.out.println("Chamando todos os itens");
		List<Filtro> listaFiltros = filtros.getLista();
		ArrayList<Item> itens = itemDao.todosItens(listaFiltros);
		return new ListaItens(itens);
	}

	@WebMethod(operationName = "CadastrarItem")
	public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario usuario,
			@WebParam(name = "item") Item item) throws AutorizacaoException {
		
		if (!tokenDao.ehValido(usuario)) {
			throw new AutorizacaoException("Autorizacao falhou");
		}
		System.out.println("Cadastrando " + item);
		itemDao.cadastrar(item);
		return item;
	}

	@WebMethod(exclude = true)
	public void outroMetodo() {
		// nao vai fazer parte do WSDL
	}

}
