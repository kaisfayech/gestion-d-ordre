package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.interfaces.InterfaceNote;
import com.example.demo.models.SecurityType;

@Service

public class ServiceNote implements InterfaceNote {
	@Autowired
	private ServiceConractTypeImpl serviceConractTypeImpl;
	public List<List<SecurityType>> response() throws JAXBException, XMLStreamException  {
		List<List<SecurityType>> note = new ArrayList <List<SecurityType>>();
		List <SecurityType> operationType = new ArrayList <SecurityType>() ;
		operationType.add(new SecurityType("ACHAT","Buy"));
		operationType.add(new SecurityType("VENTE","Sell"));
		operationType.add(new SecurityType("SOUSCR","Subscription"));
		note.add(operationType);
		List<SecurityType> pco=serviceConractTypeImpl.response("QUOTATION_PLACE");
		note.add(pco);
		List<SecurityType> med=serviceConractTypeImpl.response("MEDIA");
		note.add(med);
		List<SecurityType> sor=serviceConractTypeImpl.response("ORDER_STATUS");
		note.add(sor);	
		return note;
	}
}
