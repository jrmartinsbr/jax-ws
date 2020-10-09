package br.com.caelum.estoque.modelo.usuario;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {
	
	private String pattern = "dd/MM/yyyy";
		
	@Override
	public String marshal(Date date) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	@Override
	public Date unmarshal(String dateString) throws Exception {
		return new SimpleDateFormat(pattern).parse(dateString);
	}

}
